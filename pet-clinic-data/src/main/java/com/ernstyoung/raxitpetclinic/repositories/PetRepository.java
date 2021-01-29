package com.ernstyoung.raxitpetclinic.repositories;

import com.ernstyoung.raxitpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
