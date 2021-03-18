package com.ernstyoung.raxitpetclinic.controllers;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.model.Pet;
import com.ernstyoung.raxitpetclinic.model.PetType;
import com.ernstyoung.raxitpetclinic.services.OwnerService;
import com.ernstyoung.raxitpetclinic.services.PetService;
import com.ernstyoung.raxitpetclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class PetControllerTest {

    @InjectMocks
    PetController petController;

    @Mock
    OwnerService ownerService;

    @Mock
    PetService petService;

    @Mock
    PetTypeService petTypeService;

    Owner owner;
    Set<PetType> petTypes;
    MockMvc mockMvc;
    private static final String VIEWS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";

    @BeforeEach
    void setUp() {
        owner = Owner.builder().id(1L).build();
        petTypes = new HashSet<>();
        petTypes.add(PetType.builder().id(1L).name("Dog").build());
        petTypes.add(PetType.builder().id(2L).name("Cat").build());
        mockMvc = MockMvcBuilders.standaloneSetup(petController).build();
        when(ownerService.findById(anyLong())).thenReturn(owner);
        when(petTypeService.findAll()).thenReturn(petTypes);
    }

    @Test
    void initCreateForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("types"));
    }

    @Test
    void processCreateForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("birthDate", "2019-11-03")
                .param("name", "Oscar"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(petService).save(any());
    }

    @Test
    void initUpdateForm() throws Exception {
        when(petService.findById(anyLong())).thenReturn(Pet.builder().id(1L).build());
        mockMvc.perform(get("/owners/1/pets/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_CREATE_OR_UPDATE_PET_FORM))
                .andExpect(model().attributeExists("owner"))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processUpdateForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
        verify(petService).save(any());
    }
}