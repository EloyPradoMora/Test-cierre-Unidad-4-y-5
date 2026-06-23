package com.example.demo;

public class ErrorDetalle {
    private String campo;
    private String mensaje;

    public ErrorDetalle(String campo, String mensaje) {
        this.campo = campo;
        this.mensaje = mensaje;
    }

    public String getCampo() { return campo; }
    public String getMensaje() { return mensaje; }
}