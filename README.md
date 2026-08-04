#  Nginx Log Analyzer (Java CLI)

Uma ferramenta de linha de comando (CLI) focada em performance para analisar logs gigantescos de servidores Nginx, extrair estatísticas de acesso e exportar relatórios em formato JSON.

Este projeto foi construído com foco em **Eficiência de Memória** e **Engenharia de Software**, processando arquivos sem sobrecarregar a memória RAM.

##  Funcionalidades

*   **Processamento de Arquivos Grandes:** Utiliza a API `java.nio.file.Files` e Streams para ler o arquivo linha por linha (Lazy Evaluation), evitando o erro `OutOfMemory`.
*   **Análise via Regex:** Quebra linhas complexas do Nginx Combined Log Format usando expressões regulares estruturadas.
*   **Domínio Imutável:** Representação dos dados lidos utilizando Java `Records`.
*   **Interface rica no terminal:** Argumentos validados e interface de ajuda (`--help`) construída com Picocli.
*   **Exportação:** Agrupa dados (Top IPs, Porcentagem de Erros) e exporta um relatório legível em `.json` utilizando Jackson.

## ️ Tecnologias Utilizadas

*   **Java 21** ( mínimo 14 para Records )
*   **Maven** (Gerenciamento de dependências e automação de build)
*   **Picocli** (Criação da interface de linha de comando)
*   **Jackson** (Serialização de objetos Java para JSON)
*   **Maven Shade Plugin** (Geração de Fat JAR executável)

##  Como executar o projeto na sua máquina

**Pré-requisitos:** Você precisa ter o [Java JDK](https://adoptium.net/) e o [Maven](https://maven.apache.org/) instalados e configurados nas variáveis de ambiente.

1. Clone o repositório:
```bash
git clone [https://github.com/devpablovieira/log-analyzer.git](https://github.com/devpablovieira/log-analyzer.git)
cd log-analyzer