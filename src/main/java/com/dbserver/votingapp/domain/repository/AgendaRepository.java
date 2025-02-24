package com.dbserver.votingapp.domain.repository;

import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
}
