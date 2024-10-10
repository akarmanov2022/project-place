package net.akarmanov.projectplace.api.user;

import lombok.extern.slf4j.Slf4j;
import net.akarmanov.projectplace.models.ExceptionResponseModel;
import net.akarmanov.projectplace.services.exceptions.PhotoNotFoundException;
import net.akarmanov.projectplace.services.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(basePackageClasses = UserRestController.class)
public class UserControllerAdvice {
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, PhotoNotFoundException.class})
    public ResponseEntity<ExceptionResponseModel> userNotFoundHandler(UserNotFoundException ex) {
        log.error("Resource not found", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ExceptionResponseModel.builder().message(ex.getMessage()).build());
    }
}
