package com.dbserver.votingapp.domain.service;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.repository.AssociateRepository;
import com.dbserver.votingapp.interfaces.converter.AssociateMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociateServiceTest {

    @Mock
    private AssociateRepository repository;

    @Mock
    private AssociateMapper mapper;

    @InjectMocks
    private AssociateService associateService;

    @Test
    void createAssociate_shouldSaveAndReturnId_whenRequestBodyIsValid() {
        CreateAssociateRequestBody requestBody = new CreateAssociateRequestBody();
        AssociateEntity mockEntity = new AssociateEntity();
        mockEntity.setId(1L);
        when(mapper.toEntity(any(CreateAssociateRequestBody.class))).thenReturn(mockEntity);
        when(repository.save(any(AssociateEntity.class))).thenReturn(mockEntity);

        Long returnedId = associateService.createAssociate(requestBody);

        assertEquals(1L, returnedId);
        verify(mapper).toEntity(requestBody);
        verify(repository).save(mockEntity);
    }
}