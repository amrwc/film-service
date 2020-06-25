package dev.amrw.filmservice.domain.model;

import com.mongodb.lang.NonNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Entity representing a film.
 */
@Document
@CompoundIndex(name = "title_year_director", def = "{'title': 1, 'year': 1, 'director': 1}", unique = true)
public class Film {

    @Id
    private String id;

    @NonNull
    private final String title;

    @NonNull
    private final String year;

    @NonNull
    private final String director;

    @NonNull
    private final String url;

    private Film(final Builder builder) {
        title = builder.title;
        year = builder.year;
        director = builder.director;
        url = builder.url;
    }

    public String getId() {
        return id;
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
                .append(id, film.id)
                .append(title, film.title)
                .append(year, film.year)
                .append(director, film.director)
                .append(url, film.url)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
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
