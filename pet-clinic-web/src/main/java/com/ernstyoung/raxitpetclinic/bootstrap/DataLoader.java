package com.ernstyoung.raxitpetclinic.bootstrap;

import com.ernstyoung.raxitpetclinic.model.Owner;
import com.ernstyoung.raxitpetclinic.model.Vet;
import com.ernstyoung.raxitpetclinic.services.OwnerService;
import com.ernstyoung.raxitpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {

        Owner owner1 = new Owner();
        owner1.setId(1L);
        owner1.setFirstName("Raxit");
        owner1.setLastName("Jain");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2L);
        owner2.setFirstName("Michael");
        owner2.setLastName("Jackson");

        ownerService.save(owner2);

        System.out.println("Loaded Owners!!");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Magnus");
        vet1.setLastName("Carlsen");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(1L);
        vet2.setFirstName("Vishwanathan");
        vet2.setLastName("Anand");

        vetService.save(vet2);

        System.out.println("Loaded Vets!!");

    }
}
