package com.lyzunyk.footballmanager.exception;

public class ProcessTransferException extends IllegalArgumentException {
    private static final String PROCESS_TRANSFER_EXCEPTION = "Transfer failed";

    public ProcessTransferException(String message) {
        super(message.isEmpty() ? PROCESS_TRANSFER_EXCEPTION : message);
    }

    public ProcessTransferException() {
        super(PROCESS_TRANSFER_EXCEPTION);
    }
}
