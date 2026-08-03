package com.pablovieira.loganalyzer;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class LogParser {
    private static final Pattern padrao = Pattern.compile("^(\\S+) - - \\[(.*?)\\] \"([A-Z]+) (.*?) HTTP/.*\" (\\d{3}) .*$");
    public LogReport processarArquivo(Path caminhoDoArquivo) {
        LogReport relatorio = new LogReport();
        try(Stream<String> linhas = Files.lines(caminhoDoArquivo)) {
            linhas
                    .map(this::converterLinhaParaRecord)
                    .filter(entry -> entry != null)
                    .forEach(relatorio::registrarAcesso);
        } catch (Exception e) {
            System.err.println("Erro ao ler linhas para arquivo: " + e.getMessage());
        }
        return relatorio;
    }

    private LogEntry converterLinhaParaRecord(String linha) {
        Matcher matcher = padrao .matcher(linha);
        if (matcher.matches()) {
            String ip = matcher.group(1);
            String dataString = matcher.group(2); // "10/Jul/2026:10:15:32 +0000"
            String metodo = matcher.group(3);
            String caminho = matcher.group(4);
            int status = Integer.parseInt(matcher.group(5));


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ss Z", Locale.ENGLISH);
            ZonedDateTime data = ZonedDateTime.parse(dataString, formatter);


            return new LogEntry(ip, data, metodo, caminho, status);
        }
        return null;
    }
}
