package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.domain.Breed;
import fi.haagahelia.catmarketplace.domain.Cat;
import fi.haagahelia.catmarketplace.domain.CatRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CatRepositoryTest {
    @Autowired
    private CatRepository catRepository;

    @Test
    public void findByName(){
        List<Cat> cats = catRepository.findByName("Prince");
        assertThat(cats).hasSize(1);
        assertThat(cats.get(0).getDescription().equals("clever and lazy"));
    }

    @Test
    public void createNewCat(){
        Cat cat = new Cat("Tibi", "sophisticated", 5, 450.0, new Breed("Sphynx"));
        catRepository.save(cat);
        assertThat(cat.getCatId()).isNotNull();
        assertThat(cat.getName().equals("Tibi"));
    }

    @Test
    @Rollback(false)
    public void deleteCat(){
        Cat cat = catRepository.findByName("Prince").get(0);
        catRepository.delete(cat);
        Optional<Cat> deleteCat = catRepository.findById(cat.getCatId());
        assertThat(deleteCat).isEmpty();
    }
}
