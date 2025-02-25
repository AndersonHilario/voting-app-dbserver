package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.agenda.CreateAgendaRequestBody;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AgendaRepository;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AgendaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AgendaServiceTest {

    @InjectMocks
    private AgendaService agendaService;

    @Mock
    private AgendaMapper mapper;

    @Mock
    private AgendaRepository agendaRepository;

    @Mock
    private AssociateRepository associateRepository;

    @Test
    void createAgenda_successfulCreation_returnsSavedAgendaId() {
        CreateAgendaRequestBody requestBody = new CreateAgendaRequestBody();
        requestBody.setAssociateId(1L);
        requestBody.setName("Test Agenda");

        AssociateEntity associateEntity = new AssociateEntity();
        associateEntity.setId(1L);

        AgendaEntity agendaEntity = new AgendaEntity();
        agendaEntity.setName("Test Agenda");

        AgendaEntity savedEntity = new AgendaEntity();
        savedEntity.setId(100L);

        when(associateRepository.findById(1L)).thenReturn(Optional.of(associateEntity));
        when(mapper.toEntity(requestBody)).thenReturn(agendaEntity);
        when(agendaRepository.save(any(AgendaEntity.class))).thenReturn(savedEntity);

        Long result = agendaService.createAgenda(requestBody);

        assertEquals(100L, result);
        verify(associateRepository).findById(1L);
        verify(mapper).toEntity(requestBody);
        verify(agendaRepository).save(agendaEntity);
    }

    @Test
    void createAgenda_associateNotFound_throwsRuntimeException() {
        CreateAgendaRequestBody requestBody = new CreateAgendaRequestBody();
        requestBody.setAssociateId(2L);

        when(associateRepository.findById(2L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> agendaService.createAgenda(requestBody));
        assertEquals("Associate not found with id: 2.", exception.getMessage());
        verify(associateRepository).findById(2L);
        verifyNoInteractions(mapper);
        verifyNoInteractions(agendaRepository);
    }
}