package gunsnhoney.sfpetclinic.service.map;

import gunsnhoney.sfpetclinic.model.Person;
import gunsnhoney.sfpetclinic.service.CrudService;

import java.util.Set;

public class PersonServiceMap extends AbstractMapService<Person, Long> implements CrudService<Person, Long> {

    @Override
    public Set<Person> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Person object) {
        super.delete(object);
    }

    @Override
    public Person save(Person object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Person findById(Long id) {
        return super.findById(id);
    }
}
