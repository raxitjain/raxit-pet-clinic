package com.ernstyoung.raxitpetclinic.services.springdatajpa;

import com.ernstyoung.raxitpetclinic.model.Specialty;
import com.ernstyoung.raxitpetclinic.repositories.SpecialtyRepository;
import com.ernstyoung.raxitpetclinic.services.SpecialtiesService;

import java.util.HashSet;
import java.util.Set;

public class SpecialtyJpaService implements SpecialtiesService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyJpaService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Set<Specialty> findAll() {
        Set<Specialty> specialties = new HashSet<>();
        specialtyRepository.findAll().forEach(specialties::add);
        return specialties;
    }

    @Override
    public Specialty findById(Long id) {
        return specialtyRepository.findById(id).orElse(null);
    }

    @Override
    public Specialty save(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public void delete(Specialty specialty) {
        specialtyRepository.delete(specialty);
    }

    @Override
    public void deleteById(Long id) {
        specialtyRepository.deleteById(id);
    }
}
