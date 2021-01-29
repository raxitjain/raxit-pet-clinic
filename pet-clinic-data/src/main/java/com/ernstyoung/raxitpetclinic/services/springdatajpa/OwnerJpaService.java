package com.ernstyoung.raxitpetclinic.services.springdatajpa;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.repositories.OwnerRepository;
import com.ernstyoung.raxitpetclinic.repositories.PetRepository;
import com.ernstyoung.raxitpetclinic.repositories.PetTypeRepository;
import com.ernstyoung.raxitpetclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("springdatajpa")
public class OwnerJpaService implements OwnerService {

    private final PetTypeRepository petTypeRepository;
    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public OwnerJpaService(PetTypeRepository petTypeRepository, PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petTypeRepository = petTypeRepository;
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long id) {
        return ownerRepository.findById(id).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        ownerRepository.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }
}
