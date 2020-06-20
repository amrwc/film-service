package dev.amrw.filmservice.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class UrlUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "'https://www.imdb.com/title/tt5013056', 'tt5013056'",
            "'https://www.imdb.com/title/tt3393786/?ref_=tt_sims_tti', 'tt3393786'",
            "'tt0387564', 'tt0387564'"
    })
    @DisplayName("Should have gotten IMDB ID")
    void shouldHaveGottenImdbId(final String input, final String expectedResult) {
        assertThat(UrlUtils.getImdbId(input)).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "'https://www.imdb.com/title/tt5013056', 'https://www.imdb.com/title/tt5013056'",
            "'https://www.imdb.com/title/tt3393786/?ref_=tt_sims_tti', 'https://www.imdb.com/title/tt3393786'"
    })
    @DisplayName("Should have gotten plain IMDB URL")
    void shouldHaveGottenPlainImdbUrl(final String input, final String expectedResult) {
        assertThat(UrlUtils.getPlainImdbUrl(input)).isEqualTo(expectedResult);
    }
}
