package com.dbserver.votingapp.aplication.vote;

import com.dbserver.votingapp.domain.model.vote.VoteType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommitVoteRequestBody {

    private Long votingSessionId;
    private Long associateId;
    private VoteType voteType;
    private LocalDateTime votedAt;

}
