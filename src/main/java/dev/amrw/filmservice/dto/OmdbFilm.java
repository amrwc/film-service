package dev.amrw.filmservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO describing film entity responses from OMDB API.
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
}
