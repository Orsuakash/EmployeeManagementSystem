package com.example.Employee.GlobleException;

import com.example.Employee.DTO.ExpectionDto;
import com.example.Employee.DTO.ValidationExpectionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Expectionhandel {

    @ExceptionHandler(RunTimeExpectionNotFound.class)
    public ResponseEntity<ExpectionDto> handleExcepcionNotFound
            (ExpectionDto ex, HttpServletRequest request) {
        ExpectionDto expectionDto = new ExpectionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(expectionDto);

    }


    // metheod arugeement
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExpectionDto> handleValidationExpection
    (MethodArgumentNotValidException ex, HttpServletRequest request) {

        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().
                getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(),
                                error.getDefaultMessage()
                        )
                );
        ValidationExpectionDto vexpectionDto= new ValidationExpectionDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "validation failed ",
                request.getRequestURI(),
                fieldErrors

        );

return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(vexpectionDto);
    }





    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExpectionDto> handleExcepcionNotFound
            (RuntimeException ex, HttpServletRequest request) {
        ExpectionDto expectionDto = new ExpectionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(expectionDto);

    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExpectionDto> handleExpection
            (ExpectionDto ex, HttpServletRequest request) {
        ExpectionDto expectionDto = new ExpectionDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(expectionDto);

    }
}

