package com.dbserver.votingapp.interfaces.converter;

import com.dbserver.votingapp.aplication.associate.CreateAssociateRequestBody;
import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AgendaMapperImpl.class})
public interface AssociateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAgendas", ignore = true)
    @Mapping(target = "votes", ignore = true)
    AssociateEntity toEntity(CreateAssociateRequestBody dto);

}
