package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AssociateMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

import static com.dbserver.votingapp.domain.model.associate.AssociateValidatedCpf.ABLE_TO_VOTE;
import static com.dbserver.votingapp.domain.model.associate.AssociateValidatedCpf.UNABLE_TO_VOTE;

@Slf4j
@Service
@AllArgsConstructor
public class AssociateService {

    private AssociateRepository repository;
    private AssociateMapper mapper;
    private final Random random = new Random();


    @Transactional
    public Long createAssociate(CreateAssociateRequestBody requestBody) {
        AssociateEntity associateEntity = mapper.toEntity(requestBody);

        log.info("Creating new associate with name: {}", associateEntity.getName());
        AssociateEntity savedEntity = repository.save(associateEntity);
        return savedEntity.getId();
    }

    public String validateCpf() {
        if (random.nextBoolean()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid CPF!");
        }

        return random.nextBoolean() ? ABLE_TO_VOTE.toString() : UNABLE_TO_VOTE.toString();
    }

}
