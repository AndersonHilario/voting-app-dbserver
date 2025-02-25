package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.agenda.CreateAgendaRequestBody;
import com.dbserver.votingapp.domain.service.AgendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/agendas")
@Tag(name = "Agendas", description = "Agendas API")
public class AgendaController {

    private AgendaService service;

    @PostMapping("/create-agenda")
    @Operation(summary = "Create a new agenda")
    public ResponseEntity<String> createAgenda(@RequestBody CreateAgendaRequestBody requestBody) {
        Long agendaId = service.createAgenda(requestBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Agenda created successfully! Agenda Id: " + agendaId + ".");
    }

}
