package gunsnhoney.sfpetclinic.service;

import gunsnhoney.sfpetclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
