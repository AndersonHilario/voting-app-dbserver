package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import com.dbserver.votingapp.domain.repository.AgendaRepository;
import com.dbserver.votingapp.domain.repository.VotingSessionRepository;
import com.dbserver.votingapp.interfaces.converter.VotingSessionMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VotingSessionServiceTest {

    @InjectMocks
    private VotingSessionService votingSessionService;

    @Mock
    private VotingSessionMapper votingSessionMapper;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private AgendaRepository agendaRepository;

    @Test
    void shouldCreateVotingSessionSuccessfully() {
        Long agendaId = 1L;
        Long expectedVotingSessionId = 100L;

        CreateVotingSessionRequestBody requestBody = new CreateVotingSessionRequestBody();
        requestBody.setAgendaId(agendaId);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setId(agendaId);
        agendaEntity.setName("Test Agenda");

        VotingSessionEntity votingSessionEntity = new VotingSessionEntity();
        VotingSessionEntity savedEntity = new VotingSessionEntity();
        savedEntity.setId(expectedVotingSessionId);

        Mockito.when(agendaRepository.findById(agendaId)).thenReturn(Optional.of(agendaEntity));
        Mockito.when(votingSessionMapper.toEntity(requestBody)).thenReturn(votingSessionEntity);
        Mockito.when(votingSessionRepository.save(votingSessionEntity)).thenReturn(savedEntity);

        Long actualVotingSessionId = votingSessionService.createVotingSession(requestBody);

        assertEquals(expectedVotingSessionId, actualVotingSessionId);
        Mockito.verify(agendaRepository).findById(agendaId);
        Mockito.verify(votingSessionMapper).toEntity(requestBody);
        Mockito.verify(votingSessionRepository).save(votingSessionEntity);
    }

    @Test
    void shouldThrowExceptionWhenAgendaNotFound() {
        Long agendaId = 1L;

        CreateVotingSessionRequestBody requestBody = new CreateVotingSessionRequestBody();
        requestBody.setAgendaId(agendaId);

        Mockito.when(agendaRepository.findById(agendaId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> votingSessionService.createVotingSession(requestBody));
        assertEquals("Agenda not found with id: " + agendaId + ".", exception.getMessage());
        Mockito.verify(agendaRepository).findById(agendaId);
        Mockito.verifyNoInteractions(votingSessionMapper);
        Mockito.verifyNoInteractions(votingSessionRepository);
    }
}