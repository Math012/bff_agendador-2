package com.math012.bff.infra.execptions;

public class BusinessException extends RuntimeException {

    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}