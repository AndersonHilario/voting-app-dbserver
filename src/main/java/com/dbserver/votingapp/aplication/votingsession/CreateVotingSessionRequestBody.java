package com.dbserver.votingapp.aplication.votingsession;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateVotingSessionRequestBody {

    private String name;
    private String description;
    private Integer timeLimit;
    private Long agendaId;
    private LocalDateTime createdAt;

}
