package com.lyzunyk.footballmanager.exception;

public class ProcessPaymentException extends IllegalArgumentException{
    private static final String PROCESS_PAYMENT_EXCEPTION = "Payment failed";

    public ProcessPaymentException(String message){
        super(message.isEmpty() ? PROCESS_PAYMENT_EXCEPTION : message);
    }

    public ProcessPaymentException(){
        super(PROCESS_PAYMENT_EXCEPTION);
    }
}
