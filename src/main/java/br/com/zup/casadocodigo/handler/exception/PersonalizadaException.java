package br.com.zup.casadocodigo.handler.exception;

public class PersonalizadaException extends RuntimeException {

    private String campo;

    public PersonalizadaException(String message, String campo) {
        super(message);
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }
}

