package com.pablovieira.loganalyzer;

import java.time.ZonedDateTime;

public record LogEntry(
    String ipAdress,
    ZonedDateTime timestamp,
    String httpMethod,
    String path,
    int statusCode
) {
}
