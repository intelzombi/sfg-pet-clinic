package gunsnhoney.sfpetclinic.model;

import java.time.LocalDate;

public class Visit extends BaseEntity {

    private LocalDate data;
    private String Description;
    private Pet pet;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
