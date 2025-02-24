package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.vote.CommitVoteRequestBody;
import com.dbserver.votingapp.domain.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    private VoteService service;

    @PostMapping("/commit-vote")
    public ResponseEntity<String> vote(@RequestBody CommitVoteRequestBody requestBody) {
        Long voteId = service.commitVote(requestBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Vote commited successfully! Vote Id: " + voteId + ".");
    }

}
