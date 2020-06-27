package dev.amrw.filmservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;
import java.util.Map;

import static org.apache.commons.lang3.ArrayUtils.toStringArray;

/**
 * {@link Object} array validator for {@link NotBlankCollection}.
 */
public class NotBlankCollectionValidatorForArray implements ConstraintValidator<NotBlankCollection, Object[]> {

    @Override
    public boolean isValid(final Object[] array, final ConstraintValidatorContext context) {
        if (array == null || array.length == 0) {
            return false;
        }
        for (final Object element : array) {
            if (element == null) {
                return false;
            }
        }
        final Object element = array[0];
        if (element instanceof CharSequence) {
            return isValidCharSequenceArray(array);
        } else if (element instanceof Collection) {
            return isValidCollectionArray(array);
        } else if (element instanceof Map) {
            return isValidMapArray(array);
        } else if (element instanceof Object[]) {
            return isValidObjectArray(array);
        } else {
            return true;
        }
    }

    /**
     * Determines whether the given {@link CharSequence} array is valid, i.e. each element passes the
     * {@link String#isBlank} check.
     * @param charSequences {@link Object} array that can be cast to {@link CharSequence}
     * @return {@code boolean} whether the {@link CharSequence} array is valid
     * @throws NullPointerException if array contains {@code null}
     */
    private boolean isValidCharSequenceArray(final Object[] charSequences) throws NullPointerException {
        for (final CharSequence charSequence : toStringArray(charSequences)) {
            if (charSequence.toString().isBlank()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the given {@link Collection} array is valid, i.e. each element passes the
     * {@link Collection#isEmpty} check.
     * @param collections {@link Object} array that can be cast to {@link Collection}
     * @return {@code boolean} whether the {@link Collection} array is valid
     * @throws NullPointerException if array contains {@code null}
     */
    private boolean isValidCollectionArray(final Object[] collections) throws NullPointerException {
        for (final Object collection : collections) {
            if (((Collection) collection).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines whether the given {@link Map} array is valid, i.e. each element passes the {@link Map#isEmpty} check.
     * @param maps {@link Object} array that can be cast to {@link Collection}
     * @return {@code boolean} whether the {@link Collection} array is valid
     * @throws NullPointerException if array contains {@code null}
     */
    private boolean isValidMapArray(final Object[] maps) {
        for (final Object map : maps) {
            if (((Map) map).isEmpty()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Validates {@link Object} array.
     * <p>
     * NOTE: This method should be used to prevent stack overflow when recursively calling {@link #isValid}.
     * @param objects {@link Object} array
     * @return {@code boolean} whether the given {@link Object} array is valid
     */
    private boolean isValidObjectArray(final Object[] objects) {
        if (objects == null || objects.length == 0) {
            return false;
        }
        for (final Object object : objects) {
            if (object == null
                    || object instanceof Object[] // Disallow further Object arrays
                    || (object instanceof CharSequence && !isValidCharSequenceArray(objects))
                    || object instanceof Collection  // Disallow further Collections
                    || object instanceof Map // Disallow further Maps
            ) {
                return false;
            }
        }
        return true;
    }
}
