package com.ernstyoung.raxitpetclinic.bootstrap;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.model.Pet;
import com.ernstyoung.raxitpetclinic.model.PetType;
import com.ernstyoung.raxitpetclinic.model.Vet;
import com.ernstyoung.raxitpetclinic.services.OwnerService;
import com.ernstyoung.raxitpetclinic.services.PetTypeService;
import com.ernstyoung.raxitpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatPetType = petTypeService.save(cat);

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

        ownerService.save(owner1);

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

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Vishwanathan");
        vet2.setLastName("Anand");

        vetService.save(vet2);

        System.out.println("Loaded Vets!!");

    }
}
