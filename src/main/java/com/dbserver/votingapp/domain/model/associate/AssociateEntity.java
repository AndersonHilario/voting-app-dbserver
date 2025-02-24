package com.dbserver.votingapp.domain.model.associate;

import com.dbserver.votingapp.domain.model.vote.VoteEntity;
import com.dbserver.votingapp.domain.model.agenda.AgendaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "associate")
public class AssociateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<AgendaEntity> createdAgendas;

    @OneToMany(mappedBy = "associate", cascade = CascadeType.ALL)
    private List<VoteEntity> votes;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String cpf;

}
