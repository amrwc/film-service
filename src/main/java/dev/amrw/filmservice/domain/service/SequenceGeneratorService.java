package dev.amrw.filmservice.domain.service;

import dev.amrw.filmservice.domain.model.DatabaseSequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Service for generating database sequences.
 */
@Service
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    public SequenceGeneratorService(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * Gets next value in the given sequence.
     * @param sequenceName name of the database sequence to use
     * @return next value; defaults to {@code 1L} if it's the first value
     */
    public long nextVal(final String sequenceName) {
        final Query query = Query.query(Criteria.where("_id").is(sequenceName));
        final UpdateDefinition updateDefinition = new Update().inc("seq", 1);
        final FindAndModifyOptions options = FindAndModifyOptions.options().returnNew(true).upsert(true);
        final DatabaseSequence counter = mongoOperations.findAndModify(
                query, updateDefinition, options, DatabaseSequence.class);
        return Objects.isNull(counter) ? 1L : counter.getSeq();
    }
}
