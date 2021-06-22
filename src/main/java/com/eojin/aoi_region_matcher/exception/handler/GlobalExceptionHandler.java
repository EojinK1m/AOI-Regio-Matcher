package com.eojin.aoi_region_matcher.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e){
        final ErrorCode errorCode = e.getErrorCode();

        return new ResponseEntity<>(new ErrorResponse(
                errorCode.getMessage()),
                HttpStatus.valueOf(errorCode.getStatus())
        );
    }
}
