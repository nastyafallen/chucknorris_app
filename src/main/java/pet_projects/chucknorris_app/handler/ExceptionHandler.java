package pet_projects.chucknorris_app.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pet_projects.chucknorris_app.exception.NoSuchCategoryException;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<String> handleNoSuchCategoryException(NoSuchCategoryException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Такая категория не найдена!");
    }

}
