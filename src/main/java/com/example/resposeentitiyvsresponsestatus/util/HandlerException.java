package com.example.resposeentitiyvsresponsestatus.util;

import com.example.resposeentitiyvsresponsestatus.exception.PessoaNotFoundException;
import com.example.resposeentitiyvsresponsestatus.exception.RequestParamNotExistException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.NoResultException;
import java.util.Map;
@RestControllerAdvice
public class HandlerException {


    @ExceptionHandler(PessoaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> PessoaNotFoundException(PessoaNotFoundException e){
        return Map.of("messagem",e.getMessage());
    }

    @ExceptionHandler(RequestParamNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String,String> RequestParamNotExistException(RequestParamNotExistException e){
        return Map.of("messagem",e.getMessage());
    }


}
