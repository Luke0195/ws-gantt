package br.com.plannermanager.bunisses.exceptions;

public class InvalidParamException extends RuntimeException{
    public InvalidParamException(String message){
        super(message);
    }
}
