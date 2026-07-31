package com.pablovieira.loganalyzer;

import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import junit.framework.TestCase;
import org.junit.Test;


public class LogParserTest extends TestCase {

    @Test
    public void testdeveRetornarDataFormatada(){
        LogParser logParser = new LogParser();
        Path path = FileSystems.getDefault().getPath("data","access.log");
        assertEquals(null,logParser.processarArquivo(path));
    }

}