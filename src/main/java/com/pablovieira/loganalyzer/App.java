package com.pablovieira.loganalyzer;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(name = "log-analyzer", mixinStandardHelpOptions = true, version = "1.0",
        description = "Analisa logs do Nginx e exporta estatísticas.")

public class App implements Callable<Integer> {

    @Option(names = {"-i", "--input"}, required = true, description = "Caminho do arquivo de log (ex: data/access.log)")
    private Path arquivoEntrada;

    @Option(names = {"-o", "--output"}, description = "Onde salvar o relatório", defaultValue = "relatorio.json")
    private Path arquivoSaida;

    @Override
    public Integer call() throws Exception {
        System.out.println("Iniciando análise do arquivo: " + arquivoEntrada);

        LogParser parser = new LogParser();
        LogReport relatorio = parser.processarArquivo(arquivoEntrada);

        com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        mapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);

        System.out.println("Gerando relatório em: " + arquivoSaida);
        mapper.writeValue(arquivoSaida.toFile(), relatorio);

        System.out.println("Análise concluída com sucesso!");

        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
