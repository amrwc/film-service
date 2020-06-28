package dev.amrw.filmservice.domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.assertj.core.api.Assertions.assertThat;

class DatabaseSequenceTest {

    private long seq;
    private DatabaseSequence sequence;

    @BeforeEach
    void beforeEach() {
        seq = nextLong();
        sequence = new DatabaseSequence();
        sequence.setSeq(seq);
    }

    @Test
    void shouldHaveSetAllFieldsCorrectly() {
        assertThat(sequence.getId()).isNull();
        assertThat(sequence.getSeq()).isEqualTo(seq);
    }
}
