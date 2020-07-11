package dev.amrw.filmservice.domain.event;

import dev.amrw.filmservice.domain.model.Film;
import dev.amrw.filmservice.domain.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmModelListenerTest {

    @Mock
    private SequenceGeneratorService sequenceGenerator;
    @InjectMocks
    FilmModelListener listener;

    @Mock
    private Film film;
    @Mock
    private BeforeConvertEvent<Film> event;

    @BeforeEach
    void beforeEach() {
        when(event.getSource()).thenReturn(film);
    }

    @Test
    void shouldNotHaveAddedIdBeforeConvertWhenItHasAlreadyBeenSet() {
        when(film.getId()).thenReturn(11L);
        listener.onBeforeConvert(event);
        verifyNoMoreInteractions(film);
    }

    @Test
    void shouldHaveAddedIdBeforeConvert() {
        final long nextVal = nextLong();
        when(film.getId()).thenReturn(0L);
        when(sequenceGenerator.nextVal(Film.SEQUENCE_NAME)).thenReturn(nextVal);
        listener.onBeforeConvert(event);
        verify(film).setId(eq(nextVal));
    }
}
