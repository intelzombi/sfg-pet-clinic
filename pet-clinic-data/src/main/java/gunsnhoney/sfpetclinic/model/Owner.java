package gunsnhoney.sfpetclinic.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person {

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String telephone, Set<Pet> pets) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
        if (pets != null) {
            pets.forEach(pet -> {
                pet.setOwner(this);
            });
            this.pets.addAll(pets);
        }
    }

    //this.to.that
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private final Set<Pet> pets = new HashSet<>();
    @Column(name = "address")
    private String address;
    @Column(name = "city")
    private String city;
    @Column(name = "telephone")
    private String telephone;

    public void addPet(Pet pet) {
        this.pets.add(pet);
    }
}
