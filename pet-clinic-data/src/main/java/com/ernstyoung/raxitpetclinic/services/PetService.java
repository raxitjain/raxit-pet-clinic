package com.ernstyoung.raxitpetclinic.services;

import com.ernstyoung.raxitpetclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);
    Pet save(Pet pet);
    Set<Pet> findAll();

}
