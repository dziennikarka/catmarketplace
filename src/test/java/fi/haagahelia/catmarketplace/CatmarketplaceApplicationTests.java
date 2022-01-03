package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.web.BreedController;
import fi.haagahelia.catmarketplace.web.CatController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Testing that the context is creating controllers
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class CatmarketplaceApplicationTests {
	@Autowired
	private BreedController breedController;

	@Autowired
	private CatController catController;

	@Test
	void contextLoads() {


	}

}
