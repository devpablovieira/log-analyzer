package com.pablovieira.loganalyzer;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogReportTest {
    @Test
     void deveRetornarTop5Ips(){
        LogReport report = new LogReport();
        Map<String, Long> result = report.getTop5Ips();
        assertTrue(result.isEmpty());
    }

    @Test
    void deveRetornarPorcentagemZero() {
        LogReport report = new LogReport();
        assertEquals(0.0, report.getPorcentagemDeErro());
    }

    @Test
    void deveContabilizarComoErros() {
        LogReport report = new LogReport();
        report.registrarAcesso(new LogEntry("192.168.0.1", null,null,null, 399));
        report.registrarAcesso(new LogEntry("192.168.0.2", null,null,null,400));
        report.registrarAcesso(new LogEntry("192.168.0.3", null,null,null,500));

        assertEquals(3, report.getTotalRequisicoes());
        assertEquals(2, report.getTotalErros());

        assertEquals(66.666, report.getPorcentagemDeErro(), 0.001);
    }

    @Test
    void deveSomarAcessosDoMesmoIp() {
        LogReport report = new LogReport();
        report.registrarAcesso(new LogEntry("10.0.0.1",  null,null,null,200));
        report.registrarAcesso(new LogEntry("10.0.0.1",  null,null,null,200));

        Map<String, Long> acessos = report.getAcessosPorIp();

        assertEquals(1, acessos.size());
        assertEquals(2L, acessos.get("10.0.0.1"));
    }

    @Test
    void deveRetornarOs5IpsComMaisAcessos() {
        LogReport report = new LogReport();
        registrarVariosAcessos("IpA", 1, report);
        registrarVariosAcessos("IpB", 2, report);
        registrarVariosAcessos("IpC", 3, report);
        registrarVariosAcessos("IpD", 4, report);
        registrarVariosAcessos("IpE", 5, report);
        registrarVariosAcessos("IpF", 6, report);

        Map<String, Long> top5 = report.getTop5Ips();

        assertEquals(5, top5.size());
        assertFalse(top5.containsKey("IpA"), "O IP com menor número de acessos deve ser descartado");

        String primeiroDaLista = top5.keySet().iterator().next();
        assertEquals("IpF", primeiroDaLista);
    }

    private void registrarVariosAcessos(String ip, int quantidade, LogReport report) {
        for (int i = 0; i < quantidade; i++) {
            report.registrarAcesso(new LogEntry(ip,  null,null,null,200));
        }
    }

}