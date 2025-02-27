package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.votingsession.CreateVotingSessionRequestBody;
import com.dbserver.votingapp.aplication.votingsession.VotingSessionResultResponseBody;
import com.dbserver.votingapp.domain.service.VotingSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/voting-sessions")
@Tag(name = "Voting Sessions", description = "Voting Sessions API")
public class VotingSessionController {

    private VotingSessionService service;

    @PostMapping("/create-voting-session")
    @Operation(summary = "Create a new voting session")
    public ResponseEntity<String> createAssociate(@RequestBody CreateVotingSessionRequestBody requestBody) {
        Long votingSessionId = service.createVotingSession(requestBody);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Voting Session created successfully! Voting Session Id: " + votingSessionId + ".");
    }

    @GetMapping("/{votingSessionId}/get-voting-session-result")
    @Operation(summary = "Get the voting session result")
    public ResponseEntity<VotingSessionResultResponseBody> getVotingSessionResult(@PathVariable Long votingSessionId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(service.getVotingSessionResult(votingSessionId));
    }

}
