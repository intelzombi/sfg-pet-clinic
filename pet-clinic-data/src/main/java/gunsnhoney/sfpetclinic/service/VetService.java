package gunsnhoney.sfpetclinic.service;


import gunsnhoney.sfpetclinic.model.Vet;

import java.util.Set;

public interface VetService {
    Vet findById(Long id);

    Vet findByVetName(String lastName);

    Vet save(Vet vet);

    Set<Vet> findAll();
}
