package com.pablovieira.loganalyzer;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class LogParserTest {
    @Test
    void deveRetornarArquivoValido(@TempDir Path temp) throws IOException {
        LogParser logParser = new LogParser();
        Path arquivo = temp.resolve("acessos.log");
        Files.writeString(arquivo,"37.187.101.43 - - [19/May/2015:03:05:22 +0000] \"GET /downloads/product_2 HTTP/1.1\" 404 337 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"\n" +
                "144.76.4.49 - - [19/May/2015:03:05:25 +0000] \"GET /downloads/product_1 HTTP/1.1\" 304 0 \"-\" \"Debian APT-HTTP/1.3 (0.9.7.9)\"");
        LogReport result = logParser.processarArquivo(arquivo);
        assertNotNull(result);
    }

    @Test
    void deveLancarExcecao(@TempDir Path temp){
        try{
            LogParser parser = new LogParser();
            Path arquivoInexistente = temp.resolve("arquivo_fantasma.log");
            parser.processarArquivo(arquivoInexistente);
            fail();
        } catch (UncheckedIOException e) {
            assertEquals("Erro ao ler o arquivo", e.getMessage());
        }
    }

    @Test
    void deveRetornarRelatorioVazio(@TempDir Path temp) throws IOException {
        Path arquivo = temp.resolve("vazio.log");
        Files.createFile(arquivo);

        LogParser parser = new LogParser();
        LogReport relatorio = parser.processarArquivo(arquivo);

        assertNotNull(relatorio);
    }

    @Test
    void deveConverterLinhaValidaParaLogEntry(){
        LogParser parser = new LogParser();
        String linhaValida = "192.168.1.50 - - [10/Jul/2026:10:15:32 +0000] \"GET /api/users HTTP/1.1\" 200 512 \"-\" \"Mozilla/5.0\"";

        LogEntry entry = parser.converterLinhaParaRecord(linhaValida);
        assertNotNull(entry);
        assertEquals("192.168.1.50", entry.ipAddress());
        assertEquals("GET", entry.httpMethod());
        assertEquals(200, entry.statusCode());
    }

    @Test
    void deveRetornarNullConversaoLinhaParaRecord(){
        LogParser parser = new LogParser();
        String linhaInvalida = "aaaaaaaaaaaaaaaaaaaaaaaaa";
        LogEntry entry = parser.converterLinhaParaRecord(linhaInvalida);
        assertNull(entry);
    }

}