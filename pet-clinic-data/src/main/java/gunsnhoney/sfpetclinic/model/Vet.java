package gunsnhoney.sfpetclinic.model;

import java.util.HashSet;
import java.util.Set;

public class Vet extends Person {
    private final Set<Specialty> specialties = new HashSet<>();

    public Set<Specialty> getSpecialties() {
        return specialties;
    }

    public void addSpecialty(Specialty specialty) {
        this.specialties.add(specialty);
    }
}
