package dev.amrw.filmservice.domain.service;

import dev.amrw.filmservice.domain.model.Film;
import dev.amrw.filmservice.domain.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmServiceTest {

    @Mock
    private FilmRepository repository;
    @InjectMocks
    private FilmService service;
    @Captor
    private ArgumentCaptor<List<Film>> filmsCaptor;

    private String title;
    private String year;
    private String director;
    private String url;
    @Mock
    dev.amrw.filmservice.dto.Film filmDto;

    @BeforeEach
    void beforeEach() {
        title = randomAlphabetic(10);
        year = String.valueOf(nextInt(1700, 9999));
        director = randomAlphabetic(10);
        url = randomAlphabetic(10);
        when(filmDto.getTitle()).thenReturn(title);
        when(filmDto.getYear()).thenReturn(year);
        when(filmDto.getDirector()).thenReturn(director);
        when(filmDto.getUrl()).thenReturn(url);
    }

    @Test
    void shouldHaveSavedFilms() {
        final List<Film> films = List.of(new Film(title, year, director, url));
        when(repository.saveAll(anyList())).thenReturn(films);
        assertThat(service.saveAll(List.of(filmDto))).isEqualTo(films);
        verify(repository).saveAll(filmsCaptor.capture());
        assertThat(filmsCaptor.getValue()).isEqualTo(films);
    }
}
