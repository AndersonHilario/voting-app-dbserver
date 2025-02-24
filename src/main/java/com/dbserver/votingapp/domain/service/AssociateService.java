package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AssociateMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AssociateService {

    private AssociateRepository repository;
    private AssociateMapper mapper;

    public Long createAssociate(CreateAssociateRequestBody requestBody) {
        AssociateEntity associateEntity = mapper.toEntity(requestBody);

        AssociateEntity savedEntity = repository.save(associateEntity);
        return savedEntity.getId();
    }

}
