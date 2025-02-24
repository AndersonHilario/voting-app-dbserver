package com.dbserver.votingapp.domain.repository;

import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {
}
