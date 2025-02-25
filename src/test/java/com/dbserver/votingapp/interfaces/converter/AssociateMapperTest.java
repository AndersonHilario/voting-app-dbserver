package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AssociateMapperTest {

    private final AssociateMapper mapper = Mappers.getMapper(AssociateMapper.class);

    @Test
    void toEntity_ShouldMapFieldsCorrectly() {
        CreateAssociateRequestBody requestBody = new CreateAssociateRequestBody();
        requestBody.setName("John Doe");
        requestBody.setCpf("12345678901");

        AssociateEntity entity = mapper.toEntity(requestBody);

        assertNull(entity.getId(), "ID should be null as it is ignored in mapping");
        assertEquals("John Doe", entity.getName(), "Name should match the value from the DTO");
        assertEquals("12345678901", entity.getCpf(), "CPF should match the value from the DTO");
        assertNull(entity.getCreatedAgendas(), "Created agendas should be null as they are ignored in mapping");
        assertNull(entity.getVotes(), "Votes should be null as they are ignored in mapping");
    }

    @Test
    void toEntity_ShouldHandleNullDto() {
        AssociateEntity entity = mapper.toEntity(null);

        assertNull(entity, "Entity should be null when the DTO is null");
    }
}