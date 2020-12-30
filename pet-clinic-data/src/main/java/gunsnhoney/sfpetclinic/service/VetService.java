package gunsnhoney.sfpetclinic.service;


import gunsnhoney.sfpetclinic.model.Vet;

public interface VetService extends CrudService<Vet, Long> {
    Vet findByLastName(String lastName);
}
