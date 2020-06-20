package dev.amrw.filmservice.util;

import dev.amrw.filmservice.config.Configuration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for working with URLs.
 */
public final class UrlUtils {

    /**
     * Parses the given IMDB URL and returns the IMDB film ID.
     * <p>
     * The ID takes has the following format:
     * <pre> {@code
     *     tt0000000
     * } </pre>
     * @param url IMDB URL
     * @return IMDB ID, or an empty string if the ID couldn't be found in the given URL
     */
    public static String getImdbId(final String url) {
        final Matcher matcher = Pattern.compile("tt\\d+").matcher(url);
        return matcher.find() ? matcher.group(0) : "";
    }

    /**
     * IMDB URLs may come in different forms and may contain redundant information for this context. This method
     * removes redundant information from the URL and returns a clean share link.
     * <p>
     * Example of an IMDB URL with additional {@code ref} details:
     * <p>
     * <pre> {@code
     *     https://www.imdb.com/title/tt0133093/?ref_=fn_al_tt_1
     * } </pre>
     * @return plain IMDB URL
     */
    public static String getPlainImdbUrl(final String url) {
        final String filmId = getImdbId(url);
        return filmId.isEmpty() ? url : Configuration.IMDB_BASE_URL + "/title/" + filmId;
    }
}
