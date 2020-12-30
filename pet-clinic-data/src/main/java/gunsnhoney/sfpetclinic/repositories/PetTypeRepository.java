package gunsnhoney.sfpetclinic.repositories;

import gunsnhoney.sfpetclinic.model.PetType;
import org.springframework.data.repository.CrudRepository;

public interface PetTypeRepository extends CrudRepository<PetType, Long> {
}
