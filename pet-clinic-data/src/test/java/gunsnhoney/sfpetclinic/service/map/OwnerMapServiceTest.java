package gunsnhoney.sfpetclinic.service.map;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Pet;
import gunsnhoney.sfpetclinic.model.PetType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OwnerMapServiceTest {

    final Long idValue = 1L;
    final String lastName = "Smooth";
    final Set<Pet> pets = new HashSet<>();
    private OwnerMapService ownerMapService;
    private PetTypeMapService petTypeMapService;

    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        petTypeMapService = new PetTypeMapService();
//        Set<Pet> pets = new HashSet<>();
//        pets.add(Pet.builder().name("pet").visits(new HashSet<Visit>()).build());
        PetType savedDogType = petTypeMapService.save(PetType.builder().name("Dog").build());

        Pet carlsDog = Pet.builder()
                .name("Fido")
                .birthDate(LocalDate.now())
                .type(savedDogType)
                .build();

        pets.add(carlsDog);
        Owner carl = Owner.builder()
                .id(idValue)
                .firstName("Carl")
                .lastName(lastName)
                .address("333 Wafting in Glory Lane")
                .city("Oclare")
                .telephone("333 555 2212")
                .pets(pets)
                .build();

//        Owner owner = Owner.builder().id(idValue).lastName(lastName).build();
        ownerMapService.save(carl);
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(idValue));
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void saveExisting() {
        String o2LastName = "Rough";
        Long id = 2L;
        Owner owner2 = Owner.builder().id(id).pets(pets).lastName(o2LastName).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertEquals(o2LastName, savedOwner.getLastName());
        assertEquals(savedOwner.getId(), id);
    }

    @Test
    void saveNoId() {
        PetType savedDogType = petTypeMapService.save(PetType.builder().name("Dog").build());

        String o2LastName = "Rough";
        Owner owner2 = Owner.builder().pets(pets).lastName(o2LastName).build();
        Owner savedOwner = ownerMapService.save(owner2);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());

    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(idValue);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(idValue);
        assertEquals(idValue, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(idValue, owner.getId());
    }
}