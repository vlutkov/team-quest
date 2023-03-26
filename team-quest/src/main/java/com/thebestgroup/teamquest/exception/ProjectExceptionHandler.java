package com.thebestgroup.teamquest.exception;

import com.thebestgroup.teamquest.exception.type.BadRequestException;
import com.thebestgroup.teamquest.exception.type.NotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ProjectExceptionHandler {

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    public ExceptionResponse handleNotFoundException(Throwable e) {

        return buildResponse(INTERNAL_SERVER_ERROR, e);
    }

    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {

        return buildResponse(NOT_FOUND, e);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ExceptionResponse handleBadRequestException(BadRequestException e) {

        return buildResponse(BAD_REQUEST, e);
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    ExceptionResponse handleConstraintViolationException(ConstraintViolationException ex) {

        return ex.getConstraintViolations().stream()
                .findFirst()
                .map(violation -> buildResponse(BAD_REQUEST, violation.getMessage()))
                .orElse(buildResponse(INTERNAL_SERVER_ERROR, (String) null));
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        return ex.getBindingResult().getAllErrors().stream()
                .findFirst()
                .map(fieldError -> buildResponse(BAD_REQUEST, fieldError.getDefaultMessage()))
                .orElse(buildResponse(INTERNAL_SERVER_ERROR, (String) null));

    }

    private ExceptionResponse buildResponse(HttpStatus httpStatus, Throwable e) {

        return buildResponse(httpStatus, Optional.ofNullable(e)
                .map(Throwable::getMessage)
                .orElse(null));
    }

    private ExceptionResponse buildResponse(HttpStatus httpStatus, String message) {

        return ExceptionResponse.builder()
                .code(httpStatus.value())
                .status(httpStatus.getReasonPhrase())
                .message(message)
                .build();
    }
}
