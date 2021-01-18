package gunsnhoney.sfpetclinic.controllers;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Pet;
import gunsnhoney.sfpetclinic.model.PetType;
import gunsnhoney.sfpetclinic.service.OwnerService;
import gunsnhoney.sfpetclinic.service.datajpa.PetTypeJpaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerIndexControllerTest {
    @InjectMocks
    OwnerIndexController ownerIndexController;
    Set<Owner> owners = new HashSet<>();
    MockMvc mockMvc;
    @Mock
    private OwnerService ownerService;
    @Mock
    private PetTypeJpaService petTypeJpaService;

    @BeforeEach
    void setUp() {
        PetType savedDogType = petTypeJpaService.save(PetType.builder().name("Dog").build());
        PetType savedHorseType = petTypeJpaService.save(PetType.builder().name("Horse").build());
        Set<Pet> carlsPets = new HashSet<>();
        Set<Pet> sarasPets = new HashSet<>();
        Pet carlsDog = Pet.builder()
                .name("Fido")
                .birthDate(LocalDate.now())
                .type(savedDogType)
                .build();
        carlsPets.add(carlsDog);
        Pet theHorse = Pet.builder()
                .name("Winnie")
                .birthDate(LocalDate.now())
                .type(savedHorseType)
                .build();
        sarasPets.add(theHorse);
        owners.add(Owner.builder()
                .id(1L)
                .firstName("Carl")
                .lastName("Perkins")
                .address("333 Wafting in Glory Lane")
                .city("Oclare")
                .telephone("333 555 2212")
                .pets(carlsPets)
                .build());

        owners.add(Owner.builder()
                .id(1L)
                .firstName("Sara")
                .lastName("Smith")
                .address("666 Clueless Rd")
                .city("Longmont")
                .telephone("333 555 1234")
                .pets(sarasPets)
                .build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerIndexController).build();
    }

    @Test
    void listOwners() throws Exception {
        when(ownerService.findAll()).thenReturn(owners);
        mockMvc.perform(get("/owners/")).andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("notimplemented"));

    }
}