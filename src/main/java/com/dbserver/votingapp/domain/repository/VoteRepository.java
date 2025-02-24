package com.dbserver.votingapp.domain.repository;

import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import com.dbserver.votingapp.domain.model.vote.VoteType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<VoteEntity, Long> {

    Long countByAssemblyAssemblyIdAndVoteType(Long assemblyId, VoteType voteType);

}
