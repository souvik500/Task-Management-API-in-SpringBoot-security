package com.example.SpringBootExample1.Exception;

public class WrongUserActionException extends Exception{
    public WrongUserActionException(String message){
        super(message);
    }
}