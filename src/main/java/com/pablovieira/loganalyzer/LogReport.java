package com.pablovieira.loganalyzer;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class LogReport {
    private final Map<String, Long> acessosPorIp = new HashMap<>();
    private long totalRequisicoes = 0;
    private long totalErros = 0;

    public void registrarAcesso(LogEntry entry) {
        this.totalRequisicoes++;
        if(entry.statusCode() >= 400) {
            this.totalErros++;
        }
        acessosPorIp.put(entry.ipAddress(), acessosPorIp.getOrDefault(entry.ipAddress(), 0L) + 1);
    }

    public double getPorcentagemDeErro() {
        if(this.totalRequisicoes == 0L) {
            throw new IllegalArgumentException("sem requisicoes");
        }
        return (this.totalErros * 100.0 )/ this.totalRequisicoes;
    }

    public Map<String, Long> getTop5Ips() {
        return acessosPorIp.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public long getTotalRequisicoes() {
        return this.totalRequisicoes;
    }
    public long getTotalErros() {
        return this.totalErros;
    }

    public Map<String, Long> getAcessosPorIp() {
        return this.acessosPorIp;
    }

}
