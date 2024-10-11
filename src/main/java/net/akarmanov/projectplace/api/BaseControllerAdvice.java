package net.akarmanov.projectplace.api;

import lombok.extern.slf4j.Slf4j;
import net.akarmanov.projectplace.models.ExceptionResponseModel;
import net.akarmanov.projectplace.services.exceptions.PhotoNotFoundException;
import net.akarmanov.projectplace.services.exceptions.TelegramIdExistsException;
import net.akarmanov.projectplace.services.exceptions.UserExistsException;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class BaseControllerAdvice {


    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionResponseModel> badRequestHandler(Exception ex) {
        log.error("Bad request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponseModel> badRequestHandler(MethodArgumentNotValidException ex) {
        log.error("Bad request", ex);
        var errors = ex.getAllErrors();

        var message = errors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((s1, s2) -> s1 + "; " + s2)
                .orElse("Validation error");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(message).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ExceptionResponseModel> badRequestHandler(ConstraintViolationException ex) {
        log.error("Bad request", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseModel> internalServerErrorHandler(Exception ex) {
        log.error("Internal server error", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, PhotoNotFoundException.class, UsernameNotFoundException.class})
    public ResponseEntity<ExceptionResponseModel> userNotFoundHandler(Exception ex) {
        log.error("Resource not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserExistsException.class, TelegramIdExistsException.class})
    public ResponseEntity<ExceptionResponseModel> userExistsHandler(Exception ex) {
        log.error("Resource already exists", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<ExceptionResponseModel> accessDeniedHandler(Exception ex) {
        log.error("Access denied", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({DisabledException.class})
    public ResponseEntity<ExceptionResponseModel> disabledDeniedHandler(DisabledException ex) {
        log.error("Disabled exception", ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(ExceptionResponseModel.builder().message("Пользователь не подтвержден").build());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<ExceptionResponseModel> handleAuthenticationException(Exception ex) {
        log.error("Authentication error", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }
}
