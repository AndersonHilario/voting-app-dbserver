package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.vote.CommitVoteRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "votingSession", ignore = true)
    @Mapping(target = "associate", ignore = true)
    VoteEntity toEntity(CommitVoteRequestBody dto);

    @Mapping(target = "totalVotes", expression = "java(votesInFavor + votesAgainst)")
    @Mapping(target = "result", expression = "java(((votesInFavor - votesAgainst) > 0) ? com.dbserver.votingapp.domain.model.votingsession.VotingSessionResult.APPROVED : com.dbserver.votingapp.domain.model.votingsession.VotingSessionResult.REJECTED)")
    VotingSessionResultResponseBody toDto(Long votesAgainst, Long votesInFavor, Long assemblyId);

}
