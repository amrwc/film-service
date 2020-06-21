package dev.amrw.filmservice.domain.service;

import dev.amrw.filmservice.domain.model.Film;
import dev.amrw.filmservice.domain.repository.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for interacting with `films` Mongo collection via
 * {@link dev.amrw.filmservice.domain.repository.FilmRepository}.
 */
@Service
public class FilmService {

    private final FilmRepository repository;

    public FilmService(final FilmRepository repository) {
        this.repository = repository;
    }

    /**
     * Persists a {@link List} of {@link Film} instances build from the given {@link List} of
     * {@link dev.amrw.filmservice.dto.Film}.
     * @param films DTO film instances
     * @return the newly saved entities
     */
    public List<Film> saveAll(final List<dev.amrw.filmservice.dto.Film> films) {
        final List<Film> filmEntities = films.stream().map(this::translateFilmDto).collect(Collectors.toList());
        return repository.saveAll(filmEntities);
    }

    private Film translateFilmDto(final dev.amrw.filmservice.dto.Film film) {
        return new Film.Builder()
                .withTitle(film.getTitle())
                .withYear(film.getYear())
                .withDirector(film.getDirector())
                .withUrl(film.getUrl())
                .build();
    }
}
