package com.ernstyoung.raxitpetclinic.services.map;

import com.ernstyoung.raxitpetclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PetMapServiceTest {

    private PetMapService petMapService;
    private final Long PET_ID = 1L;

    @BeforeEach
    void setUp() {
        petMapService = new PetMapService();
        petMapService.save(Pet.builder().id(1L).build());
    }

    @Test
    void findAll() {
        Set<Pet> pets = petMapService.findAll();
        assertEquals(1, pets.size());
    }

    @Test
    void findById() {
        Pet pet = petMapService.findById(PET_ID);
        assertEquals(PET_ID, pet.getId());
    }

    @Test
    void findByIdNotFound() {
        Pet pet = petMapService.findById(5L);
        assertNull(pet);
    }

    @Test
    void saveWithNewId() {
        Long pet2Id = 2L;
        Pet pet2 = Pet.builder().id(pet2Id).build();
        Pet savedPet = petMapService.save(pet2);
        assertEquals(pet2Id, savedPet.getId());
        assertEquals(2, petMapService.findAll().size());
    }

    @Test
    void saveWithExisitingId() {
        Long pet2Id = 1L;
        Pet pet2 = Pet.builder().id(pet2Id).build();
        Pet savedPet = petMapService.save(pet2);
        assertEquals(pet2Id, savedPet.getId());
        assertEquals(1, petMapService.findAll().size());
    }

    @Test
    void delete() {
        petMapService.delete(petMapService.findById(PET_ID));
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteById() {
        petMapService.deleteById(PET_ID);
        assertEquals(0, petMapService.findAll().size());
    }

    @Test
    void deleteByWrongId() {
        petMapService.deleteById(2L);
        assertEquals(1, petMapService.findAll().size());
    }
}