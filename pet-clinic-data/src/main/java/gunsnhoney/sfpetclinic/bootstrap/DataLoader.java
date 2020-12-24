package gunsnhoney.sfpetclinic.bootstrap;

import gunsnhoney.sfpetclinic.model.*;
import gunsnhoney.sfpetclinic.service.OwnerService;
import gunsnhoney.sfpetclinic.service.PetTypeService;
import gunsnhoney.sfpetclinic.service.SpecialtyService;
import gunsnhoney.sfpetclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (petTypeService.findAll().isEmpty()) {
            loadData();
        } else {
            updateData();
        }

    }

    private void updateData() {

    }

    private void loadData() {
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
        //not Saved to service so we can test
        horse.setName("Horse");
        saraHorse.setType(horse);
        saraHorse.setOwner(sara);
        sara.addPet(saraHorse);
        ownerService.save(sara);
        System.out.println("Loading Owners ....");

        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        Specialty saveRadiology = specialtyService.save(radiology);
        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        Specialty saveSurgery = specialtyService.save(surgery);
        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        Specialty saveDentestry = specialtyService.save(dentistry);
        Specialty oncology = new Specialty();
        oncology.setDescription("Oncology");

        Vet drPalaro = new Vet();
        drPalaro.setFirstName("Gary");
        drPalaro.setLastName("Palaro");
        drPalaro.addSpecialty(saveDentestry);
        drPalaro.addSpecialty(saveSurgery);
        vetService.save(drPalaro);

        Vet drSevers = new Vet();
        drSevers.setFirstName("Dave");
        drSevers.setLastName("Severs");
        drSevers.addSpecialty(saveRadiology);
        drSevers.addSpecialty(saveSurgery);
        //Not saved so we can test
        drSevers.addSpecialty(oncology);
        vetService.save(drSevers);

        System.out.println("Loading Vets .....");
    }
}
