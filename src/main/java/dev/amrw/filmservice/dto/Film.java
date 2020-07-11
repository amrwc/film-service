package dev.amrw.filmservice.dto;

import dev.amrw.filmservice.config.Configuration;
import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * DTO mapping Film entity in `film` collection.
 */
public class Film {

    private final String title;
    private final String year;
    private final String director;
    private final String url;

    public Film(final OmdbFilm omdbFilm) {
        title = omdbFilm.getTitle();
        year = omdbFilm.getYear();
        director = omdbFilm.getDirector();
        url = Configuration.IMDB_BASE_URL + "/title/" + omdbFilm.getImdbId();
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Film film = (Film) o;
        return new EqualsBuilder()
                .append(title, film.title)
                .append(year, film.year)
                .append(director, film.director)
                .append(url, film.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(year)
                .append(director)
                .append(url)
                .toHashCode();
    }
}
