package dev.amrw.filmservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.Map;

import static dev.amrw.filmservice.validation.NotBlankCollection.*;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The annotated element must not be {@code null} nor empty. Additionally the element must not contain any {@code null}
 * nor empty sub-elements.
 * <p>
 * Supported types are:
 * <ul>
 *     <li>Array (array length is evaluated)</li>
 * </ul>
 * <p>
 * Supported sub-element types are:
 * <ul>
 *     <li>{@link CharSequence} (sequence length and presence of non-whitespace characters are evaluated)</li>
 *     <li>{@link Collection} (collection size is evaluated)</li>
 *     <li>{@link Map} (map size is evaluated)</li>
 *     <li>{@link Object} array (array length and the all the above rules are evaluated on the elements)</li>
 * </ul>
 * @author amrwc
 */
@Documented
@Constraint(validatedBy = {NotBlankCollectionValidatorForArray.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Repeatable(List.class)
public @interface NotBlankCollection {

    String message() default "Must not be empty nor contain blank or empty elements";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * Defines several {@link NotBlankCollection} constraints on the same element.
     * @see NotBlankCollection
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {

        NotBlankCollection[] value();
    }
}
