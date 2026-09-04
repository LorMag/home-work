package com.example.homework.excetion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<ErrorResponseDto> validateError(Exception e) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                "Что-то случилось",
                e.getMessage(),
                LocalDateTime.now()
        );
        log.error("exception: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(errorResponseDto);
    }

}
