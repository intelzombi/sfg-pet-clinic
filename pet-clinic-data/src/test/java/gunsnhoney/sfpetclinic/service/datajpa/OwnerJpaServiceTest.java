package gunsnhoney.sfpetclinic.service.datajpa;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Pet;
import gunsnhoney.sfpetclinic.model.PetType;
import gunsnhoney.sfpetclinic.repositories.OwnerRepository;
import gunsnhoney.sfpetclinic.repositories.PetRepository;
import gunsnhoney.sfpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OwnerJpaServiceTest {
    final Long idValue = 1L;
    final String lastName = "Smooth";
    final Set<Pet> pets = new HashSet<>();
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;
    @InjectMocks
    OwnerJpaService ownerJpaService;
    @InjectMocks
    PetTypeJpaService petTypeJpaService;
    Owner carl;

    @BeforeEach
    void setUp() {
        PetType savedDogType = petTypeJpaService.save(PetType.builder().name("Dog").build());

        Pet carlsDog = Pet.builder()
                .name("Fido")
                .birthDate(LocalDate.now())
                .type(savedDogType)
                .build();

        pets.add(carlsDog);
        carl = Owner.builder()
                .id(idValue)
                .firstName("Carl")
                .lastName(lastName)
                .address("333 Wafting in Glory Lane")
                .city("Oclare")
                .telephone("333 555 2212")
                .pets(pets)
                .build();

    }

    @Test
    void findByLastName() {

        //given owner::carl

        //when
        when(ownerJpaService.findByLastName(any())).thenReturn(carl);

        //then
        Owner owner = ownerJpaService.findByLastName(lastName);
        assertEquals(lastName, owner.getLastName());
        verify(ownerRepository).findByLastName(any());

    }

    @Test
    void findAll() {
        //given owner::carl

        //when
        Set<Owner> owners = new HashSet<>();
        owners.add(carl);
        when(ownerJpaService.findAll()).thenReturn(owners);

        Set<Owner> foundOwners = ownerJpaService.findAll();

        //then
        assertNotNull(foundOwners);
        assertEquals(foundOwners.size(), 1);


    }

    @Test
    void findById() {
        //when
        Optional<Owner> opOwner = Optional.of(carl);
        when(ownerRepository.findById(anyLong())).thenReturn(opOwner);

        Owner foundOwner = ownerJpaService.findById(idValue);

        assertEquals(idValue, foundOwner.getId());

    }

    @Test
    void save() {

        when(ownerRepository.save(any())).thenReturn(carl);
        Owner savedOwner = ownerJpaService.save(carl);
        assertNotNull(savedOwner);
    }

    @Test
    void delete() {
        ownerJpaService.delete(carl);
        verify(ownerRepository).delete(any());
    }

    @Test
    void deleteById() {
        ownerJpaService.deleteById(idValue);
        verify(ownerRepository).deleteById(anyLong());
    }
}