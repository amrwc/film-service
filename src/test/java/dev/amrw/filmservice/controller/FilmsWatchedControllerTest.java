package dev.amrw.filmservice.controller;

import dev.amrw.filmservice.domain.service.FilmService;
import dev.amrw.filmservice.dto.Film;
import dev.amrw.filmservice.dto.FilmsWatchedRequest;
import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import dev.amrw.filmservice.omdb.service.OmdbService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmsWatchedControllerTest {

    @Mock
    private OmdbService omdbService;
    @Mock
    private FilmService filmService;
    @InjectMocks
    private FilmsWatchedController controller;
    @Captor
    private ArgumentCaptor<List<Film>> filmsCaptor;

    @Test
    @DisplayName("Should have processed POST request to '/films-watched'")
    void shouldHaveProcessedPostRequestToFilmsWatched() {
        final List<String> imdbUrls = List.of(randomAlphabetic(10), randomAlphabetic(10));
        final FilmsWatchedRequest request = new FilmsWatchedRequest();
        request.setUrls(imdbUrls);
        final List<OmdbFilm> omdbFilms = List.of(getOmdbFilm(), getOmdbFilm());
        when(omdbService.getFilms(imdbUrls)).thenReturn(omdbFilms);
        assertThat(controller.filmsWatched(request)).isEqualTo(new ResponseEntity<>(omdbFilms, HttpStatus.OK));
        verify(omdbService).getFilms(eq(imdbUrls));
        verify(filmService).saveAll(filmsCaptor.capture());
        for (int i = 0; i < filmsCaptor.getValue().size(); i++) {
            assertThat(filmsCaptor.getValue().get(i)).isEqualTo(new Film(omdbFilms.get(i)));
        }
    }

    private static OmdbFilm getOmdbFilm() {
        final OmdbFilm omdbFilm = new OmdbFilm();
        omdbFilm.setTitle(randomAlphabetic(10));
        omdbFilm.setYear(String.valueOf(nextInt(1700, 9999)));
        omdbFilm.setDirector(randomAlphabetic(10));
        omdbFilm.setImdbId(randomAlphabetic(10));
        return omdbFilm;
    }
}
