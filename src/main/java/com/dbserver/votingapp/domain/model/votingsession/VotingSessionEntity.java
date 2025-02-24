package com.dbserver.votingapp.domain.model.votingsession;

import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "voting_session")
public class VotingSessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "agenda_id", nullable = false)
    private AgendaEntity agenda;

    @OneToMany(mappedBy = "votingSession", cascade = CascadeType.ALL)
    private List<VoteEntity> votes;

    @Enumerated(EnumType.STRING)
    private VotingSessionStatus status = VotingSessionStatus.IN_PROGRESS;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private String name;
    private String description;
    private Integer timeLimit;
    private LocalDateTime closedAt;

}
