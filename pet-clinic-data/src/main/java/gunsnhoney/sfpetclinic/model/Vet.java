package gunsnhoney.sfpetclinic.model;

import java.util.Set;

public class Vet extends Person {
    private Set<Specialty> specialties;

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void addSpecialty(Specialty specialty) {
        this.specialties.add(specialty);
    }
}
