package com.example.nuvenapi.api.filter;

import com.example.nuvenapi.domain.exception.EntityNotFoundException;
import com.example.nuvenapi.api.filter.response.ProblemBody;
import com.example.nuvenapi.domain.exception.enums.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionFilter {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ProblemBody> handleNoResourceFoundException(NoResourceFoundException e){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.RESOURCE_NOT_FOUND;

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail(String.format("Resource %s not found", e.getResourcePath()))
                .build(), status);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ProblemBody> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){

        HttpStatus status = HttpStatus.METHOD_NOT_ALLOWED;
        ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail("Http method not allowed or wrong url")
                .build(), status);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemBody> handleMethodValidationException(MethodArgumentNotValidException e){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.VALIDATION_ERROR;

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail(String.format("Field '%s' is null", e.getBindingResult().getFieldError().getField()))
                .build(), status);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ProblemBody> handleHttpMessageNotReadable(HttpMessageNotReadableException e){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.INCOMPREHENSIBLE_MESSAGE;

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail("The request body is invalid")
                .build(), status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ProblemBody> handleTypeMismatch(MethodArgumentTypeMismatchException e){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorCode errorCode = ErrorCode.INVALID_PARAMETER;

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail(String.format("The url parameter %s received the value %s which is an invalid type", e.getName(), e.getValue()))
                .build(), status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ProblemBody> handleEntityNotFound(EntityNotFoundException e) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorCode errorCode = ErrorCode.ENTITY_NOT_FOUND;
        String detail = e.getMessage();

        return new ResponseEntity<>(ProblemBody
                .builder()
                .title(errorCode.toString())
                .status(status.toString())
                .detail(detail).build(), status);
    }


}
