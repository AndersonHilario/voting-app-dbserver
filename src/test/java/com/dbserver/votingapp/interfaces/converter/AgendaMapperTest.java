package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.agenda.CreateAgendaRequestBody;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AgendaMapperTest {

    private final AgendaMapper agendaMapper = Mappers.getMapper(AgendaMapper.class);

    @Test
    void toEntity_ShouldMapRequestBodyToEntity() {
        CreateAgendaRequestBody requestBody = new CreateAgendaRequestBody();

        AgendaEntity entity = agendaMapper.toEntity(requestBody);

        assertNotNull(entity, "Mapped entity should not be null.");
        assertNull(entity.getId(), "Mapped entity's 'id' field should be null as it is explicitly ignored.");
        assertNull(entity.getCreatedBy(), "Mapped entity's 'createdBy' field should be null as it is explicitly ignored.");
    }
}