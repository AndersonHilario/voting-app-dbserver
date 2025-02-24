package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.agenda.CreateAgendaRequestBody;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AgendaRepository;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AgendaMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AgendaService {

    private AgendaMapper mapper;
    private AgendaRepository repository;
    private AssociateRepository associateRepository;

    public Long createAgenda(CreateAgendaRequestBody requestBody) {
        AssociateEntity associateEntity = associateRepository.findById(requestBody.getAssociateId())
                .orElseThrow(() -> new RuntimeException("Associate not found with id: " + requestBody.getAssociateId() + "."));

        AgendaEntity agendaEntity = mapper.toEntity(requestBody);
        agendaEntity.setCreatedBy(associateEntity);

        AgendaEntity savedEntity = repository.save(agendaEntity);
        return savedEntity.getId();
    }

}
