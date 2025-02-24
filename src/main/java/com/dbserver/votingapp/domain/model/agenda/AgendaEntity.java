package com.dbserver.votingapp.domain.model.agenda;

import com.dbserver.votingapp.domain.model.associate.AssociateEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "agenda")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    private AssociateEntity createdBy;

    private String name;
    private String description;

}
