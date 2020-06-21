package dev.amrw.filmservice.domain.service;

import dev.amrw.filmservice.domain.model.Film;
import dev.amrw.filmservice.domain.repository.FilmRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

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

    @Test
    void shouldHaveSavedFilms() {
        final String titleMock = RandomStringUtils.random(10);
        final String yearMock = String.valueOf(RandomUtils.nextInt(1700, 9999));
        final String directorMock = RandomStringUtils.random(10);
        final String urlMock = RandomStringUtils.random(10);
        final dev.amrw.filmservice.dto.Film filmDtoMock = new dev.amrw.filmservice.dto.Film.Builder()
                .withTitle(titleMock)
                .withYear(yearMock)
                .withDirector(directorMock)
                .withUrl(urlMock)
                .build();
        final List<dev.amrw.filmservice.dto.Film> filmDtosMock = List.of(filmDtoMock);
        final Film filmEntityMock = new Film.Builder()
                .withTitle(titleMock)
                .withYear(yearMock)
                .withDirector(directorMock)
                .withUrl(urlMock)
                .build();
        final List<Film> expectedResult = List.of(filmEntityMock);
        when(repository.saveAll(anyList())).thenReturn(expectedResult);
        assertThat(service.saveAll(filmDtosMock)).isEqualTo(expectedResult);
        verify(repository).saveAll(filmsCaptor.capture());
        assertThat(filmsCaptor.getValue()).isEqualTo(expectedResult);
        verifyNoMoreInteractions(repository);
    }
}
