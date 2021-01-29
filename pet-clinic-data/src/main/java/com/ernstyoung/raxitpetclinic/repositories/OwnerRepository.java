package com.ernstyoung.raxitpetclinic.repositories;

import com.ernstyoung.raxitpetclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
