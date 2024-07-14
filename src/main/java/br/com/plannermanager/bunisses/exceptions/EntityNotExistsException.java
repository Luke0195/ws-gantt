package br.com.plannermanager.bunisses.exceptions;

public class EntityNotExistsException extends  RuntimeException{

    public EntityNotExistsException(String message){
        super(message);
    }
}
