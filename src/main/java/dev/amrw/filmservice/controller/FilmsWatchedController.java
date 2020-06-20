package dev.amrw.filmservice.controller;

import dev.amrw.filmservice.model.OmdbFilm;
import dev.amrw.filmservice.service.OmdbService;
import dev.amrw.filmservice.model.FilmsWatchedRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Films watched controller.
 */
@RestController
public class FilmsWatchedController {

    private final OmdbService omdbService;

    public FilmsWatchedController(final OmdbService omdbService) {
        this.omdbService = omdbService;
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
    public ResponseEntity<OmdbFilm[]> filmsWatched(@RequestBody FilmsWatchedRequest request) {
        final OmdbFilm film = omdbService.getFilm("tt1398426");
        return new ResponseEntity<>(new OmdbFilm[] {film}, HttpStatus.OK);
    }
}
