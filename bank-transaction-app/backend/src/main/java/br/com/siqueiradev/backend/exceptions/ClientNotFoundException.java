package br.com.siqueiradev.backend.exceptions;

public class ClientNotFoundException extends Throwable {
    
    public ClientNotFoundException(String message) {
        super(message);  // Call the superclass constructor with the message
    }
}
