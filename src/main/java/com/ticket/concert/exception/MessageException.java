package com.ticket.concert.exception;

import lombok.Data;

@Data
public class MessageException extends RuntimeException {

    private String code = "200";

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, String code) {
        super(message);
        this.code = code;
    }
}
