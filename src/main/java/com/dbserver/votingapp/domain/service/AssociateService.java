package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AssociateMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class AssociateService {

    private AssociateRepository repository;
    private AssociateMapper mapper;

    public Long createAssociate(CreateAssociateRequestBody requestBody) {
        AssociateEntity associateEntity = mapper.toEntity(requestBody);

        log.info("Creating new associate with name: {}", associateEntity.getName());
        AssociateEntity savedEntity = repository.save(associateEntity);
        return savedEntity.getId();
    }

}
