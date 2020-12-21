package gunsnhoney.sfpetclinic.bootstrap;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Vet;
import gunsnhoney.sfpetclinic.service.OwnerService;
import gunsnhoney.sfpetclinic.service.VetService;
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
        owner1.setFirstName("Carl");
        owner1.setLastName("Perkins");

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setId(2l);
        owner2.setFirstName("Sara");
        owner2.setLastName("Smith");

        ownerService.save(owner2);
        System.out.println("Loading Owners ....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Gary");
        vet1.setLastName("Palaro");
        vet1.setId(3L);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(4L);
        vet2.setFirstName("Dave");
        vet2.setLastName("Severs");
        vetService.save(vet2);

        System.out.println("Loading Vets .....");
    }
}
