package org.example.sistemaprevisaotempo.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.sistemaprevisaotempo.ClimaServiceGrpc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcConfig {

    @Bean
    public ClimaServiceGrpc.ClimaServiceBlockingStub climaStub() {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return ClimaServiceGrpc.newBlockingStub(channel);
    }
}
