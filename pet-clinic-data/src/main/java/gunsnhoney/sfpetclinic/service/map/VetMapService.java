package gunsnhoney.sfpetclinic.service.map;

import gunsnhoney.sfpetclinic.model.Specialty;
import gunsnhoney.sfpetclinic.model.Vet;
import gunsnhoney.sfpetclinic.service.SpecialtyService;
import gunsnhoney.sfpetclinic.service.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialtyService specialtyService;

    public VetMapService(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet save(Vet object) {
        if (object != null) {
            if (object.getSpecialties().isEmpty()) {
                throw new RuntimeException("Vets must have a specialty");
            }
            relsolveSpecialties(object.getSpecialties());
        }
        return super.save(object);
    }

    private void relsolveSpecialties(Set<Specialty> specialties) {
        specialties.forEach(specialty -> {
            if (specialtyService.findById(specialty.getId()) == null) {
                specialty.setId(specialtyService.save(specialty).getId());
            }
        });
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet findByLastName(String lastName) {
        return null;
    }
}
