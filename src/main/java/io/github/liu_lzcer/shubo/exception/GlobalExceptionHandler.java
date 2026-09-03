package io.github.liu_lzcer.shubo.exception;

import io.github.liu_lzcer.shubo.dto.ApiResponse;
import io.github.liu_lzcer.shubo.dto.ErrorCode;
import io.github.liu_lzcer.shubo.dto.FieldErrorItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleNotFound(TaskNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>(ErrorCode.TASK_NOT_FOUND.code(), e.getMessage(), null, null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleUnknown(Exception e) {
        log.error("未处理异常", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiResponse<>(ErrorCode.INTERNAL_ERROR.code(), ErrorCode.INTERNAL_ERROR.message(), null,null));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<FieldErrorItem>>> handleValid(MethodArgumentNotValidException e) {
        List<FieldErrorItem> error = e.getBindingResult().getFieldErrors().stream()
            .map(fieldError -> new FieldErrorItem(fieldError.getField()
                ,String.valueOf(fieldError.getRejectedValue()) , fieldError.getDefaultMessage())).toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ApiResponse<>(ErrorCode.INVALID_PARAM.code(), ErrorCode.INVALID_PARAM.message(), error,null));
    }
}
