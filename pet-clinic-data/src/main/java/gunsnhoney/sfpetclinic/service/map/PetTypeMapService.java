package gunsnhoney.sfpetclinic.service.map;

import gunsnhoney.sfpetclinic.model.PetType;
import gunsnhoney.sfpetclinic.service.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default", "datamap"})
public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);

    }

    @Override
    public void delete(PetType object) {
        super.delete(object);
    }

    @Override
    public PetType save(PetType object) {
        super.save(object);
        return object;
    }

    @Override
    public PetType findById(Long id) {
        return super.findById(id);
    }
}
