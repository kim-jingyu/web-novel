package com.webnovel.global.exception;

import com.webnovel.global.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(WebnovelException.class)
    public ResponseEntity<ErrorResponse> handleWebNovelException(WebnovelException e) {
        log.warn(e.getMessage(), e);

        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity
                .status(errorCode.status())
                .body(ErrorResponse.builder()
                        .message(errorCode.message())
                        .statusCode(errorCode.status().value())
                        .build());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        log.warn(e.getMessage(), e);

        String errorMsg = Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage();
        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .message(errorMsg)
                        .statusCode(status.value()));
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception e,
            Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request
    ) {
        log.warn(e.getMessage(), e);

        return ResponseEntity
                .badRequest()
                .body(ErrorResponse.builder()
                        .message(e.getMessage())
                        .statusCode(statusCode.value())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getMessage(), e);

        return ResponseEntity
                .internalServerError()
                .body(ErrorResponse.builder()
                        .message("서버 내부에서 오류가 발생했습니다.")
                        .statusCode(500)
                        .build());
    }
}
