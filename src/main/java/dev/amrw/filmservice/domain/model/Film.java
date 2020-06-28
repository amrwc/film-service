package dev.amrw.filmservice.domain.model;

import com.mongodb.lang.NonNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Collection representing films.
 */
@Document(collection = "films")
@CompoundIndex(name = "title_year_director", def = "{'title': 1, 'year': 1, 'director': 1}", unique = true)
public class Film {

    /** Name of the sequence used to generate UUID for each item. */
    @Transient
    public static final String SEQUENCE_NAME = "films_sequence";

    @Id
    private long id;

    @NonNull
    private final String title;

    @NonNull
    private final String year;

    @NonNull
    private final String director;

    @NonNull
    private final String url;

    public Film(final String title, final String year, final String director, final String url) {
        this.title = title;
        this.year = year;
        this.director = director;
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
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
}
