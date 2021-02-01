package com.ernstyoung.raxitpetclinic.model;

import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, Set<Pet> pets, String address, String city, String telephone) {
        super(id, firstName, lastName);
        this.pets = pets;
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets = new HashSet<>();
    private String address;
    private String city;
    private String telephone;

}
