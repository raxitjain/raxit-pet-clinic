package com.ernstyoung.raxitpetclinic.bootstrap;

import com.ernstyoung.raxitpetclinic.model.*;
import com.ernstyoung.raxitpetclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtiesService specialtiesService;
    private final VisitService visitService;
    private final PetService petService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtiesService specialtiesService, VisitService visitService, PetService petService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtiesService = specialtiesService;
        this.visitService = visitService;
        this.petService = petService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");

        PetType cat = new PetType();
        cat.setName("Cat");

        PetType savedDogPetType = petTypeService.save(dog);
        PetType savedCatPetType = petTypeService.save(cat);

        Specialty radiology = new Specialty();
        radiology.setDescription("radiology");

        Specialty surgery = new Specialty();
        radiology.setDescription("surgery");

        Specialty dentistry = new Specialty();
        radiology.setDescription("dentistry");

        Specialty savedRadiology = specialtiesService.save(radiology);
        Specialty savedSurgery = specialtiesService.save(surgery);
        Specialty savedDentistry = specialtiesService.save(dentistry);

        Owner owner1 = new Owner();
        owner1.setFirstName("Raxit");
        owner1.setLastName("Jain");
        owner1.setAddress("123 Test Street");
        owner1.setCity("New York");
        owner1.setTelephone("1111111111");

        Pet raxitsPet = new Pet();
        raxitsPet.setPetType(savedDogPetType);
        raxitsPet.setOwner(owner1);
        raxitsPet.setBirthDate(LocalDate.now());
        raxitsPet.setName("Fluffy");
        owner1.getPets().add(raxitsPet);

        Visit fluffyVisit = new Visit();
        fluffyVisit.setPet(raxitsPet);
        fluffyVisit.setDescription("Bleeding gums");
        fluffyVisit.setDate(LocalDate.now());

        ownerService.save(owner1);
        visitService.save(fluffyVisit);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michael");
        owner2.setLastName("Jackson");
        owner2.setAddress("123 Ramsay Lane");
        owner2.setCity("New York");
        owner2.setTelephone("1234567890");

        Pet michaelPet = new Pet();
        michaelPet.setPetType(savedCatPetType);
        michaelPet.setOwner(owner2);
        michaelPet.setBirthDate(LocalDate.now());
        michaelPet.setName("gambit");
        owner2.getPets().add(michaelPet);

        ownerService.save(owner2);

        System.out.println("Loaded Owners!!");

        Vet vet1 = new Vet();
        vet1.setFirstName("Magnus");
        vet1.setLastName("Carlsen");
        vet1.getSpecialties().add(savedRadiology);
        vet1.getSpecialties().add(savedDentistry);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vishwanathan");
        vet2.setLastName("Anand");
        vet1.getSpecialties().add(savedSurgery);

        vetService.save(vet1);
        vetService.save(vet2);

        System.out.println("Loaded Vets!!");
    }
}
