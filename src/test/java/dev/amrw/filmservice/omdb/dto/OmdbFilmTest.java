package dev.amrw.filmservice.omdb.dto;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class OmdbFilmTest {

    private String title;
    private String year;
    private String director;
    private String imdbId;
    private OmdbFilm omdbFilm;

    @BeforeEach
    void setUp() {
        title = RandomStringUtils.random(10);
        year = String.valueOf(RandomUtils.nextInt(1700, 9999));
        director = RandomStringUtils.random(10);
        imdbId = "tt" + RandomStringUtils.randomNumeric(7);
        title = RandomStringUtils.random(10);
        omdbFilm = new OmdbFilm();
        omdbFilm.setTitle(title);
        omdbFilm.setYear(year);
        omdbFilm.setDirector(director);
        omdbFilm.setImdbId(imdbId);
    }

    @Test
    @DisplayName("Should have set all the fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(omdbFilm.getTitle()).isEqualTo(title);
        assertThat(omdbFilm.getYear()).isEqualTo(year);
        assertThat(omdbFilm.getDirector()).isEqualTo(director);
        assertThat(omdbFilm.getImdbId()).isEqualTo(imdbId);
    }
}
