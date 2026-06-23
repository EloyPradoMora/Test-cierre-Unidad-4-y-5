package com.example.demo;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ValidacionResponse {
    private boolean valido;
    private String mensaje;
    private List<ErrorDetalle> errores;
    private String timestamp;

    public ValidacionResponse(boolean valido, String mensaje) {
        this.valido = valido;
        this.mensaje = mensaje;
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    public ValidacionResponse(boolean valido, List<ErrorDetalle> errores) {
        this.valido = valido;
        this.errores = errores;
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT);
    }

    public boolean isValido() { return valido; }
    public String getMensaje() { return mensaje; }
    public List<ErrorDetalle> getErrores() { return errores; }
    public String getTimestamp() { return timestamp; }
}