package com.ggsolucoes.contratos.infrastructere.exceptions;

public class ConflictException extends RuntimeException{

    public ConflictException(String mensagen){

        super(mensagen);
    }

    public ConflictException(String mensagen, Throwable throwable){
    super(mensagen);
   }

}