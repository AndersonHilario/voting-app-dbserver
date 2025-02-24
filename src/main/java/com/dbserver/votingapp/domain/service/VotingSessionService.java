package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionStatus;
import com.dbserver.votingapp.domain.repository.AgendaRepository;
import com.dbserver.votingapp.domain.repository.VotingSessionRepository;
import com.dbserver.votingapp.interfaces.converter.VotingSessionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VotingSessionService {

    private VotingSessionMapper votingSessionMapper;
    private VotingSessionRepository votingSessionRepository;
    private AgendaRepository agendaRepository;
    private VoteService voteService;

    public Long createVotingSession(CreateVotingSessionRequestBody requestBody) {
        AgendaEntity agendaEntity = agendaRepository.findById(requestBody.getAgendaId())
                .orElseThrow(() -> new RuntimeException("Agenda not found with id: " + requestBody.getAgendaId() + "."));

        VotingSessionEntity votingSessionEntity = votingSessionMapper.toEntity(requestBody);
        votingSessionEntity.setAgenda(agendaEntity);

        VotingSessionEntity savedEntity = votingSessionRepository.save(votingSessionEntity);
        return savedEntity.getId();
    }

    public VotingSessionResultResponseBody getVotingSessionResult(Long id) {
        VotingSessionEntity votingSessionEntity = votingSessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Voting session not found with id: " + id + "."));

        votingSessionEntity.setStatus(VotingSessionStatus.FINISHED);
        votingSessionRepository.save(votingSessionEntity);
        return voteService.getVotingSessionVotes(id);
    }

}
