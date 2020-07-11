package dev.amrw.filmservice.domain.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Collection representing database sequences.
 */
@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

    public String getId() {
        return id;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(final long seq) {
        this.seq = seq;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final DatabaseSequence sequence = (DatabaseSequence) o;
        return new EqualsBuilder()
                .append(seq, sequence.seq)
                .append(id, sequence.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(seq)
                .toHashCode();
    }
}
