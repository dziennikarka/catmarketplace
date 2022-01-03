package fi.haagahelia.catmarketplace;

import static org.assertj.core.api.Assertions.assertThat;
import fi.haagahelia.catmarketplace.domain.Breed;
import fi.haagahelia.catmarketplace.domain.BreedRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BreedRepositoryTest {
    @Autowired
    private BreedRepository breedRepository;

    @Test
    public void findByName(){
        List<Breed>breeds = breedRepository.findByName("Bengal");
        assertThat(breeds).hasSize(1);
        assertThat(breeds.get(0).getBreedId().equals(2));
    }

    @Test
    public void createNewBreed(){
        Breed breed = new Breed("Sphynx");
        breedRepository.save(breed);
        assertThat(breed.getBreedId()).isNotNull();
        assertThat(breed.getName().equals("Sphynx"));
    }

    @Test
    @Rollback(false)
    public void deleteBreed(){
        Breed breed = breedRepository.findById(Long.valueOf(3)).get();
        breedRepository.delete(breed);
        Optional<Breed> deleteBreed = breedRepository.findById(Long.valueOf(3));
        assertThat(deleteBreed).isEmpty();
    }

}
