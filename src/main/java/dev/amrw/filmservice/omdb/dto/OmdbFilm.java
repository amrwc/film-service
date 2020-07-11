package dev.amrw.filmservice.omdb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * DTO describing film entity response from OMDB API.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class OmdbFilm {

    private String title;
    private String year;
    private String director;
    private String imdbId;

    public OmdbFilm() {
    }

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @JsonProperty("Year")
    public String getYear() {
        return year;
    }

    public void setYear(final String year) {
        this.year = year;
    }

    @JsonProperty("Director")
    public String getDirector() {
        return director;
    }

    public void setDirector(final String director) {
        this.director = director;
    }

    @JsonProperty("imdbID")
    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final OmdbFilm omdbFilm = (OmdbFilm) o;
        return new EqualsBuilder()
                .append(title, omdbFilm.title)
                .append(year, omdbFilm.year)
                .append(director, omdbFilm.director)
                .append(imdbId, omdbFilm.imdbId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(year)
                .append(director)
                .append(imdbId)
                .toHashCode();
    }
}
