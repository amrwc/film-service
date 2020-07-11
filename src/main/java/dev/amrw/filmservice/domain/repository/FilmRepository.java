package dev.amrw.filmservice.domain.repository;

import dev.amrw.filmservice.domain.model.Film;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for `film` Mongo collection.
 */
@Repository
public interface FilmRepository extends MongoRepository<Film, Long> {
}
