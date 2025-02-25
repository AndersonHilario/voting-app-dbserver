package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.vote.CommitVoteRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import com.dbserver.votingapp.domain.model.vote.VoteType;
import com.dbserver.votingapp.domain.repository.VotingSessionRepository;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.domain.repository.VoteRepository;
import com.dbserver.votingapp.interfaces.converter.VoteMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class VoteService {

    private VoteMapper mapper;
    private VotingSessionRepository votingSessionRepository;
    private AssociateRepository associateRepository;
    private VoteRepository voteRepository;

    public Long commitVote(CommitVoteRequestBody requestBody) {
        VotingSessionEntity votingSessionEntity = votingSessionRepository.findById(requestBody.getVotingSessionId())
                .orElseThrow(() -> new RuntimeException("Voting session not found with id: " + requestBody.getVotingSessionId() + "."));

        AssociateEntity associateEntity = associateRepository.findById(requestBody.getAssociateId())
                .orElseThrow(() -> new RuntimeException("Associate not found with id: " + requestBody.getAssociateId() + "."));

        VoteEntity voteEntity = mapper.toEntity(requestBody);
        voteEntity.setVotingSession(votingSessionEntity);
        voteEntity.setAssociate(associateEntity);

        log.info("Commiting {}'s vote in voting session {}.", associateEntity.getName(), votingSessionEntity.getName());
        VoteEntity savedEntity = voteRepository.save(voteEntity);
        return savedEntity.getId();
    }

    public VotingSessionResultResponseBody getVotingSessionVotes(Long votingSessionId) {
        log.info("Getting voting session {} votes.", votingSessionId);
        Long against = voteRepository.countByVotingSessionIdAndVoteType(votingSessionId, VoteType.AGAINST);
        Long favor = voteRepository.countByVotingSessionIdAndVoteType(votingSessionId, VoteType.FAVOR);
        return mapper.toDto(against, favor, votingSessionId);
    }
}
