package com.pablovieira.loganalyzer;

import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class LogParserTest {
    @Test
    @DisplayName("should return formatted data")
    public void formattedData(){
        LogParser logParser = new LogParser();
        Path path = FileSystems.getDefault().getPath("data","access.log");
        LogReport result = logParser.processarArquivo(path);
        assertNotNull(result);
    }

}