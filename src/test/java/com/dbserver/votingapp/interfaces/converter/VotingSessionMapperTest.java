package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class VotingSessionMapperTest {

    private final VotingSessionMapper votingSessionMapper = Mappers.getMapper(VotingSessionMapper.class);

    @Test
    void shouldMapToEntityWithTimeLimitSpecified() {
        CreateVotingSessionRequestBody dto = new CreateVotingSessionRequestBody();
        LocalDateTime createdAt = LocalDateTime.of(2023, 10, 10, 12, 0);
        dto.setCreatedAt(createdAt);
        dto.setTimeLimit(30);

        VotingSessionEntity entity = votingSessionMapper.toEntity(dto);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getAgenda());
        assertNull(entity.getVotes());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(createdAt.plusMinutes(30), entity.getClosedAt());
        assertEquals("IN_PROGRESS", entity.getStatus().name());
    }

    @Test
    void shouldMapToEntityWithDefaultTimeLimit() {
        CreateVotingSessionRequestBody dto = new CreateVotingSessionRequestBody();
        LocalDateTime createdAt = LocalDateTime.of(2023, 10, 10, 12, 0);
        dto.setCreatedAt(createdAt);
        dto.setTimeLimit(null);

        VotingSessionEntity entity = votingSessionMapper.toEntity(dto);

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getAgenda());
        assertNull(entity.getVotes());
        assertEquals(createdAt, entity.getCreatedAt());
        assertEquals(createdAt.plusMinutes(1), entity.getClosedAt());
        assertEquals("IN_PROGRESS", entity.getStatus().name());
    }
}