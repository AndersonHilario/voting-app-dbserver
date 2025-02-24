package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
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

    public void createAssembly(CreateVotingSessionRequestBody requestBody) {
        VotingSessionEntity votingSessionEntity = votingSessionMapper.toEntity(requestBody);
        votingSessionEntity.setAgenda(agendaRepository.findById(requestBody.getAgendaId()).get());
        votingSessionRepository.save(votingSessionEntity);
    }

    public VotingSessionResultResponseBody finishAssembly(Long id) {
        VotingSessionEntity votingSessionEntity = votingSessionRepository.findById(id).get();


        votingSessionEntity.setStatus(VotingSessionStatus.FINISHED);
        votingSessionRepository.save(votingSessionEntity);
        return voteService.getAssemblyVotes(id);
    }

}
