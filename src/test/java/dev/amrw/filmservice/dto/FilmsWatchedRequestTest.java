package dev.amrw.filmservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

class FilmsWatchedRequestTest {

    private String[] urls;
    private FilmsWatchedRequest request;

    @BeforeEach
    void beforeEach() {
        urls = new String[] {randomAlphabetic(10), randomAlphabetic(10)};
        request = new FilmsWatchedRequest();
        request.setUrls(urls);
    }

    @Test
    @DisplayName("Should have set all the fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(request.getUrls()).isEqualTo(urls);
    }
}
