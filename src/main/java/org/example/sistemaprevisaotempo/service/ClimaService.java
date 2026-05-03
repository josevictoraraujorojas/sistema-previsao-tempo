package org.example.sistemaprevisaotempo.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.IntStream;

@Service
public class ClimaService {

    private final Map<String, List<Double>> dados = new HashMap<>();

    public void cadastrarCidade(String nome) {
        List<Double> temps = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            temps.add(20 + Math.random() * 10);
        }

        dados.put(nome.toLowerCase(), temps);
    }

    public List<String> listarCidades() {
        return new ArrayList<>(dados.keySet());
    }

    public List<Double> obterDados(String cidade) {
        return dados.getOrDefault(cidade.toLowerCase(), List.of(0.0,0.0,0.0,0.0,0.0));
    }
}
