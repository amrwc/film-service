package dev.amrw.filmservice.omdb.dto;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OmdbFilmTest {

    private String titleMock;
    private String yearMock;
    private String directorMock;
    private String imdbIdMock;
    private OmdbFilm omdbFilm;

    @BeforeEach
    void setUp() {
        titleMock = RandomStringUtils.random(10);
        yearMock = String.valueOf(RandomUtils.nextInt(1700, 9999));
        directorMock = RandomStringUtils.random(10);
        imdbIdMock = "tt" + RandomStringUtils.randomNumeric(7);
        titleMock = RandomStringUtils.random(10);
        omdbFilm = new OmdbFilm();
        omdbFilm.setTitle(titleMock);
        omdbFilm.setYear(yearMock);
        omdbFilm.setDirector(directorMock);
        omdbFilm.setImdbId(imdbIdMock);
    }

    @Test
    @DisplayName("Should have set all fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(omdbFilm.getTitle()).isEqualTo(titleMock);
        assertThat(omdbFilm.getYear()).isEqualTo(yearMock);
        assertThat(omdbFilm.getDirector()).isEqualTo(directorMock);
        assertThat(omdbFilm.getImdbId()).isEqualTo(imdbIdMock);
    }
}
