package dev.amrw.filmservice.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class NotBlankCollectionValidatorForArrayTest {

    @Mock
    private ConstraintValidatorContext context;
    private NotBlankCollectionValidatorForArray validator;

    @BeforeEach
    void beforeEach() {
        validator = new NotBlankCollectionValidatorForArray();
    }

    @ParameterizedTest
    @MethodSource
    @DisplayName("Should have correctly validated object array")
    void shouldHaveCorrectlyValidatedObjectArray(final Object[] input, final boolean expectedResult) {
        assertThat(validator.isValid(input, context)).isEqualTo(expectedResult);
    }

    static Stream<Arguments> shouldHaveCorrectlyValidatedObjectArray() {
        return Stream.of(
                // Check whether the array is null or empty
                Arguments.of(null, false),
                Arguments.of(new Object[] {}, false),
                Arguments.of(new Object[] {new Object()}, true),

                // Check for null Object in the array
                Arguments.of(new Object[] {null}, false),
                Arguments.of(new Object[] {new Object(), null}, false),

                // Validate CharSequence array
                Arguments.of(new Object[] {""}, false),
                Arguments.of(new Object[] {" "}, false),
                Arguments.of(new Object[] {randomAlphabetic(10), ""}, false),
                Arguments.of(new Object[] {randomAlphabetic(10), randomAlphabetic(10)}, true),

                // Validate Collection array
                Arguments.of(new Object[] {List.of()}, false),
                Arguments.of(new Object[] {List.of(new Object()), List.of()}, false),
                Arguments.of(new Object[] {List.of(new Object())}, true),

                // Validate Map array
                Arguments.of(new Object[] {Map.of()}, false),
                Arguments.of(new Object[] {Map.of(new Object(), new Object()), Map.of()}, false),
                Arguments.of(new Object[] {Map.of(new Object(), new Object())}, true),

                // Validate nested Object array, Collection and Map
                Arguments.of(new Object[] {new Object[] {}}, false),
                Arguments.of(new Object[] {new Object[] {new Object()}}, false),
                Arguments.of(new Object[] {new Object[] {List.of()}}, false),
                Arguments.of(new Object[] {new Object[] {List.of(new Object())}}, false),
                Arguments.of(new Object[] {new Object[] {Map.of()}}, false),
                Arguments.of(new Object[] {new Object[] {Map.of(new Object(), new Object())}}, false)
        );
    }
}
