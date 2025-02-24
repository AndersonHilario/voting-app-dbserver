package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VotingSessionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "agenda", ignore = true)
    @Mapping(target = "closedAt", expression = "java(dto.getTimeLimit() != null ? dto.getCreatedAt().plusMinutes(dto.getTimeLimit()) : dto.getCreatedAt().plusMinutes(1))")
    VotingSessionEntity toEntity(CreateVotingSessionRequestBody dto);

}
