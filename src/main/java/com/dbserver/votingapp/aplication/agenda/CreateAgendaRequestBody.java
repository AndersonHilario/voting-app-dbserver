package com.dbserver.votingapp.aplication.agenda;

import lombok.Data;

@Data
public class CreateAgendaRequestBody {

    private String name;
    private String description;
    private Long createdBy;

}
