package com.ernstyoung.raxitpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pets")
public class Pet extends BaseEntity {

    private LocalDate birthDate;
    private String name;

    @ManyToOne
    @JoinColumn(name="pet_type_id")
    private PetType petType;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pet")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(Long id, LocalDate birthDate, String name, PetType petType, Owner owner, Set<Visit> visits) {
        super(id);
        this.birthDate = birthDate;
        this.name = name;
        this.petType = petType;
        this.owner = owner;
        if (visits != null && visits.size() > 0) {
            this.visits = visits;
        }
    }
}
