package org.example.sistemaprevisaotempo.controller;

import org.example.sistemaprevisaotempo.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    private final ClimaServiceGrpc.ClimaServiceBlockingStub stub;

    public ClimaController(ClimaServiceGrpc.ClimaServiceBlockingStub stub) {
        this.stub = stub;
    }

    @PostMapping("/cidade")
    public String cadastrar(@RequestParam String nome) {
        return stub.cadastrarCidade(
                CidadeRequest.newBuilder().setNome(nome).build()
        ).getMensagem();
    }

    @GetMapping("/cidades")
    public List<String> listar() {
        return new ArrayList<>(
                stub.listarCidades(Empty.newBuilder().build())
                        .getCidadesList()
        );
    }

    @GetMapping("/temperatura")
    public double temperatura(@RequestParam String cidade) {
        return stub.obterTemperaturaAtual(
                CidadeRequest.newBuilder().setNome(cidade).build()
        ).getTemperatura();
    }

    @GetMapping("/previsao")
    public List<?> previsao(@RequestParam String cidade) {

        return stub.previsaoCincoDias(
                        CidadeRequest.newBuilder().setNome(cidade).build()
                ).getPrevisoesList()
                .stream()
                .map(p -> java.util.Map.of(
                        "dia", p.getDia(),
                        "temperatura", p.getTemperatura()
                ))
                .toList();
    }

    @GetMapping("/estatisticas")
    public java.util.Map<String, Object> estatisticas(@RequestParam String cidade) {

        EstatisticaResponse res = stub.estatisticasClimaticas(
                CidadeRequest.newBuilder().setNome(cidade).build()
        );

        return java.util.Map.of(
                "cidade", res.getCidade(),
                "media", res.getMedia(),
                "minima", res.getMinima(),
                "maxima", res.getMaxima()
        );
    }
}