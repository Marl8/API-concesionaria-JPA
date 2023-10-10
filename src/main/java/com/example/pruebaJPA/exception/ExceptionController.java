package com.example.pruebaJPA.exception;

import com.example.pruebaJPA.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(VehiculoNotFoundException.class)
    public ResponseEntity<?> vehiculoNotFound(VehiculoNotFoundException ex){

        ErrorDto error = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehiculoClonException.class)
    public ResponseEntity<?> vehiculoClon(VehiculoClonException ex){

        ErrorDto error = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehiculoNoSaveException.class)
    public ResponseEntity<?> vehiculoNoGuardado(VehiculoNoSaveException ex){

        ErrorDto error = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VehiculoNotFoundIdException.class)
    public ResponseEntity<?> vehiculoFoundId(VehiculoNotFoundIdException ex){

        ErrorDto error = new ErrorDto(404, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
