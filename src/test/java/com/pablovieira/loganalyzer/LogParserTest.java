package com.pablovieira.loganalyzer;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertThat;


public class LogParserTest extends TestCase {

    @Test
    @DisplayName("Should return formatted data")
    public void testdeveRetornarDataFormatada(){
        LogParser logParser = new LogParser();
        Path path = FileSystems.getDefault().getPath("data","access.log");
        LogReport result = logParser.processarArquivo(path);
        assertTrue(result != null);
    }

}