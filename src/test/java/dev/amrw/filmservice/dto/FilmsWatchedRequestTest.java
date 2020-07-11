package dev.amrw.filmservice.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.Validation;
import javax.validation.Validator;
import java.util.List;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

class FilmsWatchedRequestTest {

    private List<String> urls;
    private FilmsWatchedRequest request;
    private Validator validator;

    @BeforeEach
    void beforeEach() {
        urls = List.of(randomAlphabetic(10), randomAlphabetic(10));
        request = new FilmsWatchedRequest();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    @DisplayName("Should have set all the fields correctly")
    void shouldHaveSetAllFieldsCorrectly() {
        request.setUrls(urls);
        assertThat(request.getUrls()).isEqualTo(urls);
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should have failed validation")
    void shouldHaveFailedValidation(final FilmsWatchedRequest request) {
        assertThat(validator.validate(request)).isNotEmpty();
    }

    @Test
    @DisplayName("Should have passed validation")
    void shouldHavePassedValidation() {
        request.setUrls(urls);
        assertThat(validator.validate(request)).isEmpty();
    }

    static Stream<Arguments> shouldHaveFailedValidation() {
        final FilmsWatchedRequest request1 = new FilmsWatchedRequest();
        request1.setUrls(null);
        final FilmsWatchedRequest request2 = new FilmsWatchedRequest();
        request2.setUrls(List.of());
        final FilmsWatchedRequest request3 = new FilmsWatchedRequest();
        request3.setUrls(List.of(""));
        final FilmsWatchedRequest request4 = new FilmsWatchedRequest();
        request4.setUrls(List.of("", ""));
        return Stream.of(
                Arguments.of(request1),
                Arguments.of(request2),
                Arguments.of(request3),
                Arguments.of(request4)
        );
    }
}
