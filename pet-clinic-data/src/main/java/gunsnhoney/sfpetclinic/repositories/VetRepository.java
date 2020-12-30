package gunsnhoney.sfpetclinic.repositories;

import gunsnhoney.sfpetclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository extends CrudRepository<Vet, Long> {
    Vet findByLastName(String lastName);
}
