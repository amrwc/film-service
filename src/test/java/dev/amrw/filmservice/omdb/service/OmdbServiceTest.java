package dev.amrw.filmservice.omdb.service;

import dev.amrw.filmservice.omdb.dto.OmdbFilm;
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

import static org.apache.commons.lang3.RandomStringUtils.*;
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

    private String baseUrl;

    @BeforeEach
    void beforeEach() {
        baseUrl = randomAlphabetic(10);
        when(env.getProperty("base-url")).thenReturn(baseUrl);
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        service = new OmdbService(env, restTemplateBuilder);
    }

    @Test
    @DisplayName("Should have gotten film")
    void shouldHaveGottenFilm() {
        final String imdbId = randomAlphanumeric(10);
        final OmdbFilm omdbFilm = mock(OmdbFilm.class);
        final String url = getUrl(imdbId);
        when(restTemplate.getForObject(url, OmdbFilm.class)).thenReturn(omdbFilm);
        assertThat(service.getFilm(imdbId)).isEqualTo(omdbFilm);
    }

    @Test
    @DisplayName("Should have gotten films")
    void shouldHaveGottenFilms() {
        final String imdbUrl1 = getUrl("tt" + randomNumeric(7));
        final String imdbUrl2 = getUrl("tt" + randomNumeric(7));
        final List<String> imdbUrls = List.of(imdbUrl1, imdbUrl2);
        final List<OmdbFilm> omdbFilms = List.of(mock(OmdbFilm.class), mock(OmdbFilm.class));
        when(restTemplate.getForObject(imdbUrl1, OmdbFilm.class)).thenReturn(omdbFilms.get(0));
        when(restTemplate.getForObject(imdbUrl2, OmdbFilm.class)).thenReturn(omdbFilms.get(1));
        assertThat(service.getFilms(imdbUrls)).isEqualTo(omdbFilms);
        final ArgumentCaptor<String> imdbUrlCaptor = ArgumentCaptor.forClass(String.class);
        verify(restTemplate, times(imdbUrls.size())).getForObject(imdbUrlCaptor.capture(), eq(OmdbFilm.class));
        assertThat(imdbUrlCaptor.getAllValues()).isEqualTo(imdbUrls);
    }

    private String getUrl(final String imdbId) {
        return baseUrl + "&i=" + imdbId;
    }
}
