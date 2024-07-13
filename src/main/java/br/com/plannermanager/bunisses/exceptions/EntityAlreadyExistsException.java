package br.com.plannermanager.bunisses.exceptions;


public class EntityAlreadyExistsException extends RuntimeException{

  public EntityAlreadyExistsException(String message){
     super(message);
  }

}
