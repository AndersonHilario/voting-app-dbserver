package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.service.VotingSessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/assemblies")
@AllArgsConstructor
public class VotingSessionController {

    private VotingSessionService service;

    @PostMapping("/create-voting-session")
    public ResponseEntity<String> createAssociate(@RequestBody CreateVotingSessionRequestBody requestBody) {
        service.createAssembly(requestBody);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Voting Session created successfully");
    }

    @GetMapping("/{votingSessionId}/get-voting-session-result")
    public ResponseEntity<VotingSessionResultResponseBody> finisAssembly(@PathVariable Long votingSessionId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.finishAssembly(votingSessionId));
    }

}
