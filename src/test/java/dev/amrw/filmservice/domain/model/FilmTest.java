package dev.amrw.filmservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;

class FilmTest {

    private String title;
    private String year;
    private String director;
    private String url;
    private Film film;

    @BeforeEach
    void beforeEach() {
        title = randomAlphabetic(10);
        year = String.valueOf(nextInt(1700, 9999));
        director = randomAlphabetic(10);
        url = randomAlphabetic(10);
        film = new Film.Builder()
                .withTitle(title)
                .withYear(year)
                .withDirector(director)
                .withUrl(url)
                .build();
    }

    @Test
    @DisplayName("Should have set all the fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(film.getId()).isNull();
        assertThat(film.getTitle()).isEqualTo(title);
        assertThat(film.getYear()).isEqualTo(year);
        assertThat(film.getDirector()).isEqualTo(director);
        assertThat(film.getUrl()).isEqualTo(url);
    }
}
