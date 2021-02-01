package com.ernstyoung.raxitpetclinic.services.springdatajpa;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {

    final String LAST_NAME = "Jain";
    Owner dummyOwner;

    @InjectMocks
    OwnerJpaService ownerJpaService;

    @Mock
    OwnerRepository ownerRepository;

    @BeforeEach
    void setUp() {
        dummyOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findAll() {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());
        when(ownerRepository.findAll()).thenReturn(owners);
        Set<Owner> ownerSet = ownerJpaService.findAll();

        assertNotNull(ownerSet);
        assertEquals(2, ownerSet.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(dummyOwner));
        Owner owner = ownerJpaService.findById(1L);
        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
        Owner owner = ownerJpaService.findById(1L);
        assertNull(owner);
    }

    @Test
    void save() {
        Owner owner = Owner.builder().id(1L).build();
        when(ownerRepository.save(any())).thenReturn(owner);
        Owner savedOwner = ownerJpaService.save(owner);
        assertEquals(owner.getId(), savedOwner.getId());
    }

    @Test
    void delete() {
        ownerJpaService.delete(dummyOwner);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(1L);
        verify(ownerRepository).deleteById(any());
    }

    @Test
    void findByLastName() {
        when(ownerJpaService.findByLastName(any())).thenReturn(dummyOwner);
        Owner owner = ownerJpaService.findByLastName(LAST_NAME);
        assertEquals(LAST_NAME, owner.getLastName());
        verify(ownerRepository, times(1)).findByLastName(any());
    }
}