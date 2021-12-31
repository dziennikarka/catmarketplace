package fi.haagahelia.catmarketplace.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BreedRepository extends CrudRepository<Breed, Long> {
    List<Breed> findByName(String name);
}
