package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionResult;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteMapperTest {

    private final VoteMapper voteMapper = Mappers.getMapper(VoteMapper.class);

    @Test
    void testToDto_WithMoreVotesInFavor() {
        Long votesAgainst = 3L;
        Long votesInFavor = 5L;
        Long votingSessionId = 1L;

        VotingSessionResultResponseBody response =
                voteMapper.toDto(votesAgainst, votesInFavor, votingSessionId);

        assertEquals(8L, response.getTotalVotes());
        assertEquals(VotingSessionResult.APPROVED, response.getResult());
    }

    @Test
    void testToDto_WithMoreVotesAgainst() {
        Long votesAgainst = 7L;
        Long votesInFavor = 4L;
        Long votingSessionId = 1L;

        VotingSessionResultResponseBody response =
                voteMapper.toDto(votesAgainst, votesInFavor, votingSessionId);

        assertEquals(11L, response.getTotalVotes());
        assertEquals(VotingSessionResult.REJECTED, response.getResult());
    }

    @Test
    void testToDto_WithEqualVotes() {
        Long votesAgainst = 5L;
        Long votesInFavor = 5L;
        Long votingSessionId = 1L;

        VotingSessionResultResponseBody response =
                voteMapper.toDto(votesAgainst, votesInFavor, votingSessionId);

        assertEquals(10L, response.getTotalVotes());
        assertEquals(VotingSessionResult.TIE, response.getResult());
    }
}