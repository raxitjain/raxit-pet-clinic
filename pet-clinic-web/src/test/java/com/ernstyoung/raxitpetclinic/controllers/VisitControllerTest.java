package com.ernstyoung.raxitpetclinic.controllers;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.model.Pet;
import com.ernstyoung.raxitpetclinic.model.PetType;
import com.ernstyoung.raxitpetclinic.services.PetService;
import com.ernstyoung.raxitpetclinic.services.VisitService;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @InjectMocks
    VisitController visitController;

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    MockMvc mockMvc;
    Pet pet;
    private static final String VIEWS_CREATE_OR_UPDATE_VISIT_FORM = "pets/createOrUpdateVisitForm";

    @BeforeEach
    void setUp() {
       pet = Pet.builder().id(1L)
                .visits(new HashSet<>()).owner(Owner.builder().id(1L).build())
               .petType(PetType.builder().id(1L).build()).build();
       when(petService.findById(anyLong())).thenReturn(pet);
       mockMvc = MockMvcBuilders.standaloneSetup(visitController).build();
    }

    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name(VIEWS_CREATE_OR_UPDATE_VISIT_FORM))
                .andExpect(model().attributeExists("pet"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/visits/new")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("description", "Just Another Visit")
                .param("date", "2018-11-11"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("pet", "visit"));
    }
}