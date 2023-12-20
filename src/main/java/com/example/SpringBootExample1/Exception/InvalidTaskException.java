package com.example.SpringBootExample1.Exception;

public class InvalidTaskException extends Exception{
    public InvalidTaskException(String message){
        super(message);
    }
}