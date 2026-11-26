package com.gustavo.usuario.infrastructure.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagen){

        super(mensagen);
    }

    public ConflictException(String mensagen, Throwable throwable){
    super(mensagen);
   }

}