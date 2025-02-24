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
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class VoteService {

    private VoteMapper mapper;
    private VotingSessionRepository votingSessionRepository;
    private AssociateRepository associateRepository;
    private VoteRepository voteRepository;

    public void commitVote(CommitVoteRequestBody requestBody) {
        VotingSessionEntity votingSessionEntity = votingSessionRepository.findById(requestBody.getAssemblyId()).get();
        AssociateEntity associateEntity = associateRepository.findById(requestBody.getAssociateId()).get();

        VoteEntity voteEntity = mapper.toEntity(requestBody);
        voteEntity.setVotingSession(votingSessionEntity);
        voteEntity.setAssociate(associateEntity);
        voteRepository.save(voteEntity);
    }

    public VotingSessionResultResponseBody getAssemblyVotes(Long assemblyId) {
        Long against = voteRepository.countByAssemblyAssemblyIdAndVoteType(assemblyId, VoteType.AGAINST);
        Long favor = voteRepository.countByAssemblyAssemblyIdAndVoteType(assemblyId, VoteType.FAVOR);
        return mapper.toDto(against, favor, assemblyId);
    }
}
