package dev.amrw.filmservice.service;

import dev.amrw.filmservice.dto.OmdbFilm;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OmdbServiceTest {

    @Mock
    private Environment env;
    @Mock
    private RestTemplateBuilder restTemplateBuilder;
    @Mock
    private RestTemplate restTemplate;
    private OmdbService service;

    private String baseUrlMock;

    @BeforeEach
    void beforeEach() {
        service = new OmdbService(env, restTemplateBuilder);
        baseUrlMock = RandomStringUtils.random(10);
        when(env.getProperty(eq("base-url"))).thenReturn(baseUrlMock);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        service = new OmdbService(env, restTemplateBuilder);
    }

    @Test
    @DisplayName("Should have gotten film")
    void shouldHaveGottenFilm() {
        final String imdbIdMock = RandomStringUtils.random(10);
        final OmdbFilm omdbFilmMock = new OmdbFilm();
        final String urlMock = getUrl(imdbIdMock);
        when(restTemplate.getForObject(eq(urlMock), eq(OmdbFilm.class))).thenReturn(omdbFilmMock);
        assertThat(service.getFilm(imdbIdMock)).isEqualTo(omdbFilmMock);
        verify(restTemplate).getForObject(eq(urlMock), eq(OmdbFilm.class));
        verifyNoMoreInteractions(restTemplate);
    }

    @Test
    @DisplayName("Should have gotten films")
    void shouldHaveGottenFilms() {
        final String imdbUrl1Mock = getUrl("tt" + RandomStringUtils.randomNumeric(7));
        final String imdbUrl2Mock = getUrl("tt" + RandomStringUtils.randomNumeric(7));
        final String[] imdbUrlsMock = new String[] {imdbUrl1Mock, imdbUrl2Mock};
        final List<OmdbFilm> omdbFilmsMock = List.of(new OmdbFilm(), new OmdbFilm());
        when(restTemplate.getForObject(eq(imdbUrl1Mock), eq(OmdbFilm.class))).thenReturn(omdbFilmsMock.get(0));
        when(restTemplate.getForObject(eq(imdbUrl2Mock), eq(OmdbFilm.class))).thenReturn(omdbFilmsMock.get(1));
        assertThat(service.getFilms(imdbUrlsMock)).usingRecursiveComparison().isEqualTo(omdbFilmsMock);
        final ArgumentCaptor<String> imdbUrlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate, times(2)).getForObject(imdbUrlCaptor.capture(), eq(OmdbFilm.class));
        assertThat(imdbUrlCaptor.getAllValues()).isEqualTo(List.of(imdbUrlsMock));
        verifyNoMoreInteractions(restTemplate);
    }

    private String getUrl(final String imdbId) {
        return baseUrlMock + "&i=" + imdbId;
    }
}
