package dev.amrw.filmservice.omdb.service;

import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import dev.amrw.filmservice.util.UrlUtils;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for interacting with OMDB API.
 */
@Service
public class OmdbService {

    private final String baseUrl;
    private final RestTemplate restTemplate;

    public OmdbService(final Environment env, final RestTemplateBuilder restTemplateBuilder) {
        baseUrl = env.getProperty("base-url");
        restTemplate = restTemplateBuilder.build();
    }

    /**
     * Fetches details of the film specified by the given IMDB ID.
     * <p>
     * Example of an IMDB ID:
     * <pre> {@code
     * tt1398426
     * } </pre>
     * @param imdbId the film's IMDB ID
     * @return film details described by {@link OmdbFilm} DTO
     */
    public OmdbFilm getFilm(final String imdbId) {
        final String url = baseUrl + "&i=" + imdbId;
        return restTemplate.getForObject(url, OmdbFilm.class);
    }

    /**
     * Fetches details of the films from the given IMDB URLs.
     * @param imdbUrls array of IMDB URLs
     * @return film details described by {@link OmdbFilm} DTOs
     */
    public List<OmdbFilm> getFilms(final String[] imdbUrls) {
        return Arrays.stream(imdbUrls)
                .map(url -> getFilm(UrlUtils.getImdbId(url)))
                .collect(Collectors.toList());
    }
}
