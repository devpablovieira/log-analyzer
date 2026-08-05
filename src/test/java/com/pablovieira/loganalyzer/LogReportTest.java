package com.pablovieira.loganalyzer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogReportTest {
    @Test
    @DisplayName("should return TOP 5 IPs ")
    public void topIp(){
        LogReport report = new LogReport();
        Map<String, Long> result = report.getTop5Ips();
        assertTrue(result.isEmpty());
    }
}