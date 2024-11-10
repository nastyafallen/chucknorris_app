package pet_projects.chucknorris_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import pet_projects.chucknorris_app.service.ChuckNorrisService;

import java.util.List;

@RestController
public class ChuckNorrisController {
    private final ChuckNorrisService chuckNorrisService;

    public ChuckNorrisController(ChuckNorrisService chuckNorrisService) {
        this.chuckNorrisService = chuckNorrisService;
    }

    @GetMapping("/joke")
    public ResponseEntity<String> getRandomJoke() {
        return ResponseEntity.ok(chuckNorrisService.getRandomJoke().getValue());
    }

    @GetMapping("/joke/{category}")
    public ResponseEntity<String> getCategoryJoke(@PathVariable String category) {
        return ResponseEntity.ok(chuckNorrisService.getCategoryJoke(category).getValue());
    }

    @GetMapping("/categories")
    public ResponseEntity<String> getCategories() {
        return ResponseEntity.ok("Доступные категории:\n"
                + chuckNorrisService.getCategories());
    }

    @PostMapping("/joke")
    public ResponseEntity<String> saveJoke(@RequestBody String str) {
        chuckNorrisService.saveJoke(str);
        return ResponseEntity.ok("Вы сохранили эту шутку - " + str);
    }

    @GetMapping("/allJokes")
    public ResponseEntity<List<String>> getAllSavedJokes() {
        return ResponseEntity.ok(chuckNorrisService.getAllSavedJokes());
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpClientErrorException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Такая категория шуток не найдена!");
    }
}
