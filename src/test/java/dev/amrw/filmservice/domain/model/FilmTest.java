package dev.amrw.filmservice.domain.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilmTest {

    private String titleMock;
    private String yearMock;
    private String directorMock;
    private String urlMock;
    private Film film;

    @BeforeEach
    void beforeEach() {
        titleMock = RandomStringUtils.random(10);
        yearMock = String.valueOf(RandomUtils.nextInt(1700, 9999));
        directorMock = RandomStringUtils.random(10);
        urlMock = RandomStringUtils.random(10);
        film = new Film.Builder()
                .withTitle(titleMock)
                .withYear(yearMock)
                .withDirector(directorMock)
                .withUrl(urlMock)
                .build();
    }

    @Test
    @DisplayName("Should have set all fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(film.getId()).isNull();
        assertThat(film.getTitle()).isEqualTo(titleMock);
        assertThat(film.getYear()).isEqualTo(yearMock);
        assertThat(film.getDirector()).isEqualTo(directorMock);
        assertThat(film.getUrl()).isEqualTo(urlMock);
    }
}
