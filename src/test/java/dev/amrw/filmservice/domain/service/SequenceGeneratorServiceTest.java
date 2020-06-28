package dev.amrw.filmservice.domain.service;

import dev.amrw.filmservice.domain.model.DatabaseSequence;
import org.bson.Document;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.UpdateDefinition;

import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SequenceGeneratorServiceTest {

    @Mock
    private MongoOperations mongoOperations;
    @InjectMocks
    private SequenceGeneratorService service;

    @Captor
    private ArgumentCaptor<Query> queryCaptor;
    @Captor
    private ArgumentCaptor<UpdateDefinition> updateCaptor;
    @Captor
    private ArgumentCaptor<FindAndModifyOptions> optionsCaptor;

    @ParameterizedTest
    @MethodSource
    void shouldHaveReturnedNextValueInTheSequence(final DatabaseSequence counter, final long expectedResult) {
        final String sequenceName = randomAlphabetic(10);
        when(mongoOperations.findAndModify(any(Query.class), any(UpdateDefinition.class),
                any(FindAndModifyOptions.class), any(Class.class))).thenReturn(counter);
        assertThat(service.nextVal(sequenceName)).isEqualTo(expectedResult);
        addNextValVerifications(sequenceName);
    }

    static Stream<Arguments> shouldHaveReturnedNextValueInTheSequence() {
        final long seq = nextLong();
        final DatabaseSequence counter = mock(DatabaseSequence.class);
        when(counter.getSeq()).thenReturn(seq);
        return Stream.of(
                Arguments.of(null, 1L),
                Arguments.of(counter, seq)
        );
    }

    private void addNextValVerifications(final String sequenceName) {
        verify(mongoOperations).findAndModify(
                queryCaptor.capture(), updateCaptor.capture(), optionsCaptor.capture(), eq(DatabaseSequence.class));
        assertThat(queryCaptor.getValue().getQueryObject().get("_id")).isEqualTo(sequenceName);
        final Document inc = (Document) updateCaptor.getValue().getUpdateObject().get("$inc");
        assertThat(inc.get("seq")).isEqualTo(1);
        assertThat(optionsCaptor.getValue().isReturnNew()).isTrue();
        assertThat(optionsCaptor.getValue().isUpsert()).isTrue();
    }
}
