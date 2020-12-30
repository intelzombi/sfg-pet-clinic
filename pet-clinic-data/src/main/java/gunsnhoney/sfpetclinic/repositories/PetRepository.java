package gunsnhoney.sfpetclinic.repositories;

import gunsnhoney.sfpetclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
