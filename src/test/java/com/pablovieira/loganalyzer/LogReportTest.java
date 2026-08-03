package com.pablovieira.loganalyzer;

import junit.framework.TestCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

public class LogReportTest extends TestCase {
    @Test
    @DisplayName("should return TOP 5 IPs ")
    public void returnTop(){
        LogReport report = new LogReport();
        List<Map.Entry<String, Long>> result = report.retornaTop();
        assertTrue(result.isEmpty());
    }
}