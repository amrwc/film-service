package dev.amrw.filmservice.dto;

import dev.amrw.filmservice.config.Configuration;
import dev.amrw.filmservice.omdb.dto.OmdbFilm;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * DTO mapping Film entity in `FILMS_WATCHED` table.
 */
public final class Film {

    private final String title;
    private final String year;
    private final String director;
    private final String url;

    private Film(final Builder builder) {
        title = builder.title;
        year = builder.year;
        director = builder.director;
        url = builder.url;
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

    public static Film of(final OmdbFilm omdbFilm) {
        return new Builder()
                .withTitle(omdbFilm.getTitle())
                .withYear(omdbFilm.getYear())
                .withDirector(omdbFilm.getDirector())
                .withUrl(Configuration.IMDB_BASE_URL + "/title/" + omdbFilm.getImdbId())
                .build();
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

    /**
     * Builder for {@link Film}.
     */
    public static final class Builder {

        private String title;
        private String year;
        private String director;
        private String url;

        public Builder() {
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withYear(final String year) {
            this.year = year;
            return this;
        }

        public Builder withDirector(final String director) {
            this.director = director;
            return this;
        }

        public Builder withUrl(final String url) {
            this.url = url;
            return this;
        }

        public Film build() {
            return new Film(this);
        }
    }
}
