package gunsnhoney.sfpetclinic.bootstrap;

import gunsnhoney.sfpetclinic.model.*;
import gunsnhoney.sfpetclinic.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService, VisitService visitService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
        this.visitService = visitService;
    }

    @Override
    @Transactional
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

        PetType savedDogType = petTypeService.save(PetType.builder().name("Dog").build());
        PetType savedCatType = petTypeService.save(PetType.builder().name("Cat").build());
        PetType savedBirdType = petTypeService.save(PetType.builder().name("Bird").build());
        PetType saveHorseType = petTypeService.save(PetType.builder().name("Horse").build());

        Set<Pet> carlsPets = new HashSet<>();
        Pet carlsDog = Pet.builder()
                .name("Fido")
                .birthDate(LocalDate.now())
                .type(savedDogType)
                .build();

        carlsPets.add(carlsDog);
        Owner carl = Owner.builder()
                .firstName("Carl")
                .lastName("Perkins")
                .address("333 Wafting in Glory Lane")
                .city("Oclare")
                .telephone("333 555 2212")
                .pets(carlsPets)
                .build();

        ownerService.save(carl);

        Visit fidoVisit = new Visit();
        fidoVisit.setDescription("Castration");
        fidoVisit.setPet(carlsDog);
        fidoVisit.setDate(LocalDate.now());
        visitService.save(fidoVisit);

        Set<Pet> sarasPets = new HashSet<>();
        Pet sarasCat = Pet.builder()
                .name("Fluffy")
                .birthDate(LocalDate.now())
                .type(savedCatType)
                .build();
        sarasPets.add(sarasCat);
        Pet saraBird = Pet.builder()
                .name("CatFood")
                .birthDate(LocalDate.now())
                .type(savedBirdType)
                .build();
        sarasPets.add(saraBird);
        Pet saraHorse = Pet.builder()
                .name("Winnie")
                .birthDate(LocalDate.now())
                .type(saveHorseType)
                .build();
        sarasPets.add(saraHorse);
        Owner sara = Owner.builder().
                firstName("Sara")
                .lastName("Smith")
                .address("666 Clueless Road")
                .city("Denver")
                .telephone("333 444 8888")
                .pets(sarasPets)
                .build();

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
        Specialty saveOncology = specialtyService.save(oncology);

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
