package dev.amrw.filmservice.dto;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Request object that gets POST'ed to
 * {@link dev.amrw.filmservice.controller.FilmsWatchedController#filmsWatched(FilmsWatchedRequest)} endpoint.
 */
public class FilmsWatchedRequest {

    @NotEmpty(message = "URLs cannot be empty")
    private List<@NotBlank(message = "URL cannot be blank") String> urls;

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(final List<String> urls) {
        this.urls = urls;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FilmsWatchedRequest request = (FilmsWatchedRequest) o;
        return new EqualsBuilder()
                .append(urls, request.urls)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(urls)
                .toHashCode();
    }
}
