package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.web.BreedController;
import fi.haagahelia.catmarketplace.web.CatController;
import fi.haagahelia.catmarketplace.web.UserController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;


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

	@Autowired
	private UserController userController;

	@Test
	void contextLoads() throws Exception{
		assertThat(breedController).isNotNull();
		assertThat(catController).isNotNull();
		assertThat(userController).isNotNull();
	}

}
