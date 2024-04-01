package com.algaworks.awpag.api.exceptionHandler;


import com.algaworks.awpag.domain.exception.DomainException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, 
            HttpHeaders headers, 
            HttpStatusCode status, 
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle("Um ou mais campos estão inválidos");
        problemDetail.setType(URI.create("https://algaworks.com/erros/campos-invalidos"));
        
        var filds = ex.getBindingResult().getAllErrors().stream()
                .collect(Collectors.toMap(error ->  ((FieldError)error).getField(),
                        DefaultMessageSourceResolvable::getDefaultMessage));
        
        problemDetail.setProperty("fields", filds);
        return super.handleExceptionInternal(ex, problemDetail,headers, status, request);
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleNegocio(DomainException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle(e.getMessage());
        problemDetail.setType(URI.create("https://algaworks.com/erros/regra-de-negocio"));
        
        return problemDetail;
    }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException e ) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Recurso está em uso");
        problemDetail.setType(URI.create("https://algaworks.com/erros/recurso-em-uso"));

        return problemDetail;
    }
    
}
