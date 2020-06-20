package dev.amrw.filmservice.service;

import dev.amrw.filmservice.model.OmdbFilm;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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
}
