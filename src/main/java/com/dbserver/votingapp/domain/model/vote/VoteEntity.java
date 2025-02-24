package com.dbserver.votingapp.domain.model.vote;

import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import com.dbserver.votingapp.domain.model.votingsession.VotingSessionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vote", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"voting_session_id", "associate_id"})
})
public class VoteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "voting_session_id", nullable = false)
    private VotingSessionEntity votingSession;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "associate_id", nullable = false)
    private AssociateEntity associate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteType voteType;

    @Column(nullable = false)
    private LocalDateTime votedAt;


}
