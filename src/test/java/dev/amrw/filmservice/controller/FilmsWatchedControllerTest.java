package dev.amrw.filmservice.controller;

import dev.amrw.filmservice.dto.FilmsWatchedRequest;
import dev.amrw.filmservice.dto.OmdbFilm;
import dev.amrw.filmservice.service.OmdbService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    @InjectMocks
    private FilmsWatchedController controller;

    @Test
    @DisplayName("Should have processed POST request to '/films-watched'")
    void shouldHaveProcessedPostRequestToFilmsWatched() {
        final String[] imdbUrlsMock = new String[] {RandomStringUtils.random(10), RandomStringUtils.random(10)};
        final FilmsWatchedRequest requestMock = new FilmsWatchedRequest();
        requestMock.setUrls(imdbUrlsMock);
        final List<OmdbFilm> omdbFilmsMock = List.of(new OmdbFilm(), new OmdbFilm());
        final ResponseEntity<List<OmdbFilm>> expectedResult = new ResponseEntity<>(omdbFilmsMock, HttpStatus.OK);
        when(omdbService.getFilms(eq(imdbUrlsMock))).thenReturn(omdbFilmsMock);
        assertThat(controller.filmsWatched(requestMock)).usingRecursiveComparison().isEqualTo(expectedResult);
        verify(omdbService).getFilms(eq(imdbUrlsMock));
        verifyNoMoreInteractions(omdbService);
    }
}
