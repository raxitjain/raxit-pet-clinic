package com.ernstyoung.raxitpetclinic.repositories;

import com.ernstyoung.raxitpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
