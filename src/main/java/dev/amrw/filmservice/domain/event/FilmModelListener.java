package dev.amrw.filmservice.domain.event;

import dev.amrw.filmservice.domain.model.Film;
import dev.amrw.filmservice.domain.service.SequenceGeneratorService;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

/**
 * {@link Film} collection event listener.
 */
@Component
public class FilmModelListener extends AbstractMongoEventListener<Film> {

    private final SequenceGeneratorService sequenceGenerator;

    public FilmModelListener(final SequenceGeneratorService sequenceGenerator) {
        this.sequenceGenerator = sequenceGenerator;
    }

    /**
     * Generates next ID in the sequence before persisting the item.
     * @param event {@link BeforeConvertEvent} containing {@link Film}
     */
    @Override
    public void onBeforeConvert(final BeforeConvertEvent<Film> event) {
        if (event.getSource().getId() < 1L) {
            event.getSource().setId(sequenceGenerator.nextVal(Film.SEQUENCE_NAME));
        }
    }
}
