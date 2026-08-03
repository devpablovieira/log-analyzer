package com.pablovieira.loganalyzer;

import java.time.ZonedDateTime;

public record LogEntry(
    String ipAddress,
    ZonedDateTime timestamp,
    String httpMethod,
    String path,
    int statusCode
) {
}
