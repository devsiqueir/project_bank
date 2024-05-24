package br.com.siqueiradev.backend.exceptions;

public class BankNotFoundException extends Throwable {
    
    public BankNotFoundException(String message) {
        super(message);  // Call the superclass constructor with the message
    }
}
