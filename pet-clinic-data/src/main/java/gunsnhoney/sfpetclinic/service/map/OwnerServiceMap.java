package gunsnhoney.sfpetclinic.service.map;

import gunsnhoney.sfpetclinic.model.Owner;
import gunsnhoney.sfpetclinic.model.Pet;
import gunsnhoney.sfpetclinic.service.OwnerService;
import gunsnhoney.sfpetclinic.service.PetService;
import gunsnhoney.sfpetclinic.service.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        if (object != null) {
            if (object.getPets().isEmpty()) {
                throw new RuntimeException("owner must have a pet");
            }
            resolvePets(object.getPets());
        }
        return super.save(object);
    }

    private void resolvePets(Set<Pet> pets) {
        pets.forEach(pet -> {
            if (petTypeService.findById(pet.getType().getId()) == null) {
                pet.getType().setId(petTypeService.save(pet.getType()).getId());
            }
            if (pet.getId() == null) {
                petService.save(pet);
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
