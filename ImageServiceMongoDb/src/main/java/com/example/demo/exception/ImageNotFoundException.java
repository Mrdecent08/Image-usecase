package com.example.demo.exception;

public class ImageNotFoundException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	private String message;
 
    public ImageNotFoundException() {}
 
    public ImageNotFoundException(String msg)
    {
        super(msg);
        this.message = msg;
    }
}