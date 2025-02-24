package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.vote.CommitVoteRequestBody;
import com.dbserver.votingapp.domain.service.VoteService;
import lombok.AllArgsConstructor;
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
    public void vote(@RequestBody CommitVoteRequestBody requestBody) {
        service.commitVote(requestBody);
    }

}
