package dev.amrw.filmservice.controller;

import dev.amrw.filmservice.domain.service.FilmService;
import dev.amrw.filmservice.dto.Film;
import dev.amrw.filmservice.dto.FilmsWatchedRequest;
import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import dev.amrw.filmservice.omdb.service.OmdbService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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
        final String[] imdbUrlsMock = new String[] {RandomStringUtils.random(10), RandomStringUtils.random(10)};
        final FilmsWatchedRequest requestMock = new FilmsWatchedRequest();
        requestMock.setUrls(imdbUrlsMock);
        final List<OmdbFilm> omdbFilmsMock = List.of(getOmdbFilmMock(), getOmdbFilmMock());
        when(omdbService.getFilms(eq(imdbUrlsMock))).thenReturn(omdbFilmsMock);
        assertThat(controller.filmsWatched(requestMock)).isEqualTo(new ResponseEntity<>(omdbFilmsMock, HttpStatus.OK));
        verify(omdbService).getFilms(eq(imdbUrlsMock));
        verifyNoMoreInteractions(omdbService);
        verify(filmService).saveAll(filmsCaptor.capture());
        for (int i = 0; i < filmsCaptor.getValue().size(); i++) {
            assertThat(filmsCaptor.getValue().get(i)).isEqualTo(Film.of(omdbFilmsMock.get(i)));
        }
        verifyNoMoreInteractions(filmService);
    }

    private static OmdbFilm getOmdbFilmMock() {
        final OmdbFilm omdbFilmMock = new OmdbFilm();
        omdbFilmMock.setTitle(RandomStringUtils.random(10));
        omdbFilmMock.setYear(String.valueOf(RandomUtils.nextInt(1700, 9999)));
        omdbFilmMock.setDirector(RandomStringUtils.random(10));
        omdbFilmMock.setImdbId(RandomStringUtils.random(10));
        return omdbFilmMock;
    }
}
