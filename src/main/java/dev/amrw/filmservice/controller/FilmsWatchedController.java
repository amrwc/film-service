package dev.amrw.filmservice.controller;

import dev.amrw.filmservice.domain.service.FilmService;
import dev.amrw.filmservice.dto.Film;
import dev.amrw.filmservice.dto.FilmsWatchedRequest;
import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import dev.amrw.filmservice.omdb.service.OmdbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Films watched controller.
 */
@RestController
public class FilmsWatchedController {

    private final OmdbService omdbService;
    private final FilmService filmService;

    public FilmsWatchedController(final OmdbService omdbService, final FilmService filmService) {
        this.omdbService = omdbService;
        this.filmService = filmService;
    }

    /**
     * Processes and stores a watched film.
     * <p>
     * The endpoint processes a films array in the JSON body. Example request:
     * <p>
     * <pre> {@code
     * {
     *     "urls": [
     *         "https://www.imdb.com/title/tt1242460/?ref_=nm_knf_t1",
     *         "https://www.imdb.com/title/tt1398426"
     *     ]
     * }
     * } </pre>
     * @param request {@link FilmsWatchedRequest} containing IMDB URLs
     * @return the newly created entity
     */
    @RequestMapping(value = "/films-watched", method = RequestMethod.POST)
    public ResponseEntity<List<OmdbFilm>> filmsWatched(@RequestBody FilmsWatchedRequest request) {
        final List<OmdbFilm> omdbFilms = omdbService.getFilms(request.getUrls());
        final List<Film> films = omdbFilms.stream().map(Film::new).collect(Collectors.toList());
        filmService.saveAll(films);
        return new ResponseEntity<>(omdbFilms, HttpStatus.OK);
    }
}
