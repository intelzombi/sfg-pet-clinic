package gunsnhoney.sfpetclinic.bootstrap;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Pet;
import gunsnhoney.sfpetclinic.model.PetType;
import gunsnhoney.sfpetclinic.model.Vet;
import gunsnhoney.sfpetclinic.service.OwnerService;
import gunsnhoney.sfpetclinic.service.PetTypeService;
import gunsnhoney.sfpetclinic.service.VetService;
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
        PetType savedDogType = petTypeService.save(dog);
        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);
        PetType bird = new PetType();
        bird.setName("Bird");
        PetType savedBirdType = petTypeService.save(bird);


        Owner carl = new Owner();
        carl.setFirstName("Carl");
        carl.setLastName("Perkins");
        carl.setAddress("333 Wafting in Glory Lane");
        carl.setCity("Oclare");
        carl.setTelephone("333 555 2212");
        Pet carlsDog = new Pet();
        carlsDog.setName("Fido");
        carlsDog.setBirthDate(LocalDate.now());
        carlsDog.setType(savedDogType);
        carlsDog.setOwner(carl);
        carl.addPet(carlsDog);
        ownerService.save(carl);

        Owner sara = new Owner();
        sara.setFirstName("Sara");
        sara.setLastName("Smith");
        sara.setAddress("666 Clueless Road");
        sara.setCity("Denver");
        sara.setTelephone("333 444 8888");
        Pet sarasCat = new Pet();
        sarasCat.setName("Fluffy");
        sarasCat.setBirthDate(LocalDate.now());
        sarasCat.setType(savedDogType);
        sarasCat.setOwner(sara);
        sara.addPet(sarasCat);
        Pet saraBird = new Pet();
        saraBird.setName("CatFood");
        saraBird.setBirthDate(LocalDate.now());
        saraBird.setType(savedBirdType);
        saraBird.setOwner(sara);
        sara.addPet(saraBird);
        Pet saraHorse = new Pet();
        saraHorse.setName("Winnie");
        saraHorse.setBirthDate(LocalDate.now());
        PetType horse = new PetType();
        horse.setName("Horse");
        saraHorse.setType(horse);
        saraHorse.setOwner(sara);
        sara.addPet(saraHorse);
        ownerService.save(sara);
        System.out.println("Loading Owners ....");

        Vet vet1 = new Vet();
        vet1.setFirstName("Gary");
        vet1.setLastName("Palaro");

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Dave");
        vet2.setLastName("Severs");
        vetService.save(vet2);

        System.out.println("Loading Vets .....");
    }
}
