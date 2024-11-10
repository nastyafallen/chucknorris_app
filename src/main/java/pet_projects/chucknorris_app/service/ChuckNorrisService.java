package pet_projects.chucknorris_app.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pet_projects.chucknorris_app.model.Joke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChuckNorrisService {
    private static final Path dest = Path.of("C:\\Users\\Acer\\IdeaProjects\\chucknorris_app\\my-favourite-jokes.docx");
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(ChuckNorrisService.class);

    public ChuckNorrisService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Joke getRandomJoke() {
        String url = "https://api.chucknorris.io/jokes/random";
        return restTemplate.getForObject(url, Joke.class);
    }

    public Joke getCategoryJoke(String category) {
        String url = "https://api.chucknorris.io/jokes/random?category=" + category;
        return restTemplate.getForObject(url, Joke.class);
    }

    public String getCategories() {
        String url = "https://api.chucknorris.io/jokes/categories";
        return restTemplate.getForObject(url, String.class);
    }

    public void saveJoke(String str) {
        try {
            if(Files.exists(dest)) {Files.writeString(dest, str, StandardOpenOption.APPEND);}
            else {Files.writeString(dest, str);}
            Files.writeString(dest, "\n", StandardOpenOption.APPEND);
        } catch (IOException e) {
            logger.warn("Attempt to save new joke failed.");
            e.printStackTrace();
        }
    }

    public List<String> getAllSavedJokes() {
        List<String> list = new ArrayList<>();
        try {
            list = Files.readAllLines(dest);
        } catch (IOException e) {
            logger.warn("Attempt to read saved jokes failed.");
            e.printStackTrace();
        }
        return list;
    }

}
