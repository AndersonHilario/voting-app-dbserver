package com.dbserver.votingapp.interfaces.rest;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AssociateMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associates")
@AllArgsConstructor
public class AssociateController {

    private AssociateRepository repository;
    private AssociateMapper mapper;

    @PostMapping("/createAssociate")
    public void createAssociate(@RequestBody CreateAssociateRequestBody requestBody) {
        AssociateEntity associateEntity = mapper.toEntity(requestBody);
        repository.save(associateEntity);
    }

}
