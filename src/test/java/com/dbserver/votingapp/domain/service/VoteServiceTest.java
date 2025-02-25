package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.vote.CommitVoteRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.domain.repository.VoteRepository;
import com.dbserver.votingapp.domain.repository.VotingSessionRepository;
import com.dbserver.votingapp.interfaces.converter.VoteMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;

    @Mock
    private VotingSessionRepository votingSessionRepository;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private VoteRepository voteRepository;

    @Mock
    private VoteMapper voteMapper;

    @Test
    void commitVote_ShouldSaveVoteAndReturnId_WhenAllDataIsValid() {
        CommitVoteRequestBody requestBody = new CommitVoteRequestBody();
        requestBody.setVotingSessionId(1L);
        requestBody.setAssociateId(2L);

        VotingSessionEntity votingSession = new VotingSessionEntity();
        votingSession.setId(1L);

        AssociateEntity associate = new AssociateEntity();
        associate.setId(2L);

        VoteEntity voteEntity = new VoteEntity();
        VoteEntity savedEntity = new VoteEntity();
        savedEntity.setId(10L);

        when(votingSessionRepository.findById(1L)).thenReturn(Optional.of(votingSession));
        when(associateRepository.findById(2L)).thenReturn(Optional.of(associate));
        when(voteMapper.toEntity(requestBody)).thenReturn(voteEntity);
        when(voteRepository.save(voteEntity)).thenReturn(savedEntity);

        Long resultId = voteService.commitVote(requestBody);

        assertThat(resultId).isEqualTo(10L);
        verify(votingSessionRepository).findById(1L);
        verify(associateRepository).findById(2L);
        verify(voteRepository).save(voteEntity);
    }

    @Test
    void commitVote_ShouldThrowException_WhenVotingSessionNotFound() {
        CommitVoteRequestBody requestBody = new CommitVoteRequestBody();
        requestBody.setVotingSessionId(1L);
        requestBody.setAssociateId(2L);

        when(votingSessionRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> voteService.commitVote(requestBody));
        assertThat(exception.getMessage()).isEqualTo("Voting session not found with id: 1.");
        verify(votingSessionRepository).findById(1L);
        verifyNoInteractions(associateRepository);
        verifyNoInteractions(voteRepository);
    }

    @Test
    void commitVote_ShouldThrowException_WhenAssociateNotFound() {
        CommitVoteRequestBody requestBody = new CommitVoteRequestBody();
        requestBody.setVotingSessionId(1L);
        requestBody.setAssociateId(2L);

        VotingSessionEntity votingSession = new VotingSessionEntity();
        votingSession.setId(1L);

        when(votingSessionRepository.findById(1L)).thenReturn(Optional.of(votingSession));
        when(associateRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> voteService.commitVote(requestBody));
        assertThat(exception.getMessage()).isEqualTo("Associate not found with id: 2.");
        verify(votingSessionRepository).findById(1L);
        verify(associateRepository).findById(2L);
        verifyNoInteractions(voteRepository);
    }
}