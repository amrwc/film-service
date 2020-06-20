package dev.amrw.filmservice.dto;

/**
 * Request object that gets POST'ed to
 * {@link dev.amrw.filmservice.controller.FilmsWatchedController#filmsWatched(FilmsWatchedRequest)} endpoint.
 */
public class FilmsWatchedRequest {

    private String[] urls;

    public String[] getUrls() {
        return urls;
    }

    public void setUrls(final String[] urls) {
        this.urls = urls;
    }
}
