package com.dbserver.votingapp.aplication.votingsession;

import com.dbserver.votingapp.domain.model.votingsession.VotingSessionResult;
import lombok.Data;

@Data
public class VotingSessionResultResponseBody {

    private Long votesAgainst;
    private Long votesInFavor;
    private Long totalVotes;
    private Long assemblyId;
    private VotingSessionResult result;

}
