package com.example.kanban.exception;

public class EpicNotFoundException extends RuntimeException {
    public EpicNotFoundException(String s) {
        super(s);
    }
}
