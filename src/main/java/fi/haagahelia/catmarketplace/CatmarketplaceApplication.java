package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.domain.Cat;
import fi.haagahelia.catmarketplace.domain.CatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatmarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatmarketplaceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CatRepository repository){
		return(args) ->{
			Cat c = new Cat("Bobby", "curious and elegant", 5, 20.0);
			Cat c1 = new Cat("Floppy", "calm and always sleeping", 10, 50.0);
			Cat c2 = new Cat("Prince", "clever and lazy", 2, 500.0);

			repository.save(c);
			repository.save(c1);
			repository.save(c2);
		};
	}

}
