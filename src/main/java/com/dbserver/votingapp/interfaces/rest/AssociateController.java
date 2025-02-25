package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.service.AssociateService;
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
@RequestMapping("/associates")
@Tag(name = "Associates", description = "Associates API")
public class AssociateController {

    private AssociateService service;

    @PostMapping("/create-associate")
    @Operation(summary = "Create a new associate")
    public ResponseEntity<String> createAssociate(@RequestBody CreateAssociateRequestBody requestBody) {
        Long associateId = service.createAssociate(requestBody);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Associate created successfully! Associate Id: " + associateId + ".");
    }

}
