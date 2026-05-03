package org.example.sistemaprevisaotempo.grpc;


import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.example.sistemaprevisaotempo.*;
import org.example.sistemaprevisaotempo.service.ClimaService;

import java.util.List;

@GrpcService
public class ClimaGrpcService extends ClimaServiceGrpc.ClimaServiceImplBase {

    private final ClimaService service;

    public ClimaGrpcService(ClimaService service) {
        this.service = service;
    }

    @Override
    public void cadastrarCidade(CidadeRequest request, StreamObserver<MensagemResponse> responseObserver) {

        service.cadastrarCidade(request.getNome());

        responseObserver.onNext(
                MensagemResponse.newBuilder()
                        .setMensagem("Cidade cadastrada com sucesso")
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void listarCidades(Empty request, StreamObserver<ListaCidadesResponse> responseObserver) {

        responseObserver.onNext(
                ListaCidadesResponse.newBuilder()
                        .addAllCidades(service.listarCidades())
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void obterTemperaturaAtual(CidadeRequest request, StreamObserver<TemperaturaResponse> responseObserver) {

        List<Double> temps = service.obterDados(request.getNome());

        responseObserver.onNext(
                TemperaturaResponse.newBuilder()
                        .setCidade(request.getNome())
                        .setTemperatura(temps.get(0))
                        .build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public void previsaoCincoDias(CidadeRequest request, StreamObserver<PrevisaoResponse> responseObserver) {

        List<Double> temps = service.obterDados(request.getNome());
        List<String> dias = List.of("Seg", "Ter", "Qua", "Qui", "Sex");

        PrevisaoResponse.Builder builder = PrevisaoResponse.newBuilder()
                .setCidade(request.getNome());

        for (int i = 0; i < 5; i++) {
            builder.addPrevisoes(
                    PrevisaoDia.newBuilder()
                            .setDia(dias.get(i))
                            .setTemperatura(temps.get(i))
                            .build()
            );
        }

        responseObserver.onNext(builder.build());
        responseObserver.onCompleted();
    }

    @Override
    public void estatisticasClimaticas(CidadeRequest request, StreamObserver<EstatisticaResponse> responseObserver) {

        List<Double> temps = service.obterDados(request.getNome());

        double media = temps.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double min = temps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
        double max = temps.stream().mapToDouble(Double::doubleValue).max().orElse(0);

        responseObserver.onNext(
                EstatisticaResponse.newBuilder()
                        .setCidade(request.getNome())
                        .setMedia(media)
                        .setMinima(min)
                        .setMaxima(max)
                        .build()
        );
        responseObserver.onCompleted();
    }
}