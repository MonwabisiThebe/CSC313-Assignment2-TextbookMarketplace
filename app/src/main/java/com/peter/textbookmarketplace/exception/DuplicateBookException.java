package com.peter.textbookmarketplace.exception;

public class DuplicateBookException extends Exception {
    public DuplicateBookException(String message) {
        super(message);
    }
}