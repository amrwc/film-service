package dev.amrw.filmservice.dto;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FilmsWatchedRequestTest {

    private String[] urlsMock;
    private FilmsWatchedRequest request;

    @BeforeEach
    void beforeEach() {
        urlsMock = new String[] {RandomStringUtils.random(10), RandomStringUtils.random(10)};
        request = new FilmsWatchedRequest();
        request.setUrls(urlsMock);
    }

    @Test
    @DisplayName("Should have set all fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(request.getUrls()).isEqualTo(urlsMock);
    }
}
