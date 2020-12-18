package gunsnhoney.sfpetclinic.service;

import gunsnhoney.sfpetclinic.model.Pet;

import java.util.Set;

public interface PetService {
    Pet findById(Long id);

    Pet findByPetName(String petName);

    Pet save(Pet pet);

    Set<Pet> findAll();
}
