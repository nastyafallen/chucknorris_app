package pet_projects.chucknorris_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Joke {
    @JsonProperty("value")
    private String value;

    public Joke(String value) {
        this.value = value;
    }

    public Joke() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Joke {" + value + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joke joke = (Joke) o;
        return Objects.equals(value, joke.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
