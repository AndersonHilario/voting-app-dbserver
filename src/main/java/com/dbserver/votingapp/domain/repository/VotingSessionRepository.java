package com.dbserver.votingapp.domain.repository;

import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VotingSessionRepository extends JpaRepository<VotingSessionEntity, Long> {
}
