package fi.haagahelia.catmarketplace;

import fi.haagahelia.catmarketplace.domain.Breed;
import fi.haagahelia.catmarketplace.domain.BreedRepository;
import fi.haagahelia.catmarketplace.domain.Cat;
import fi.haagahelia.catmarketplace.domain.CatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CatmarketplaceApplication {
	private static final Logger log = LoggerFactory.getLogger(CatmarketplaceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CatmarketplaceApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BreedRepository repo, CatRepository repository){
		return(args) ->{
			log.info("save a couple of breeds");
			Breed b = new Breed("American Shorthair");
			Breed b1 = new Breed("Bengal");
			Breed b2 = new Breed("Domestic Long Hair");

			repo.save(b);
			repo.save(b1);
			repo.save(b2);

			log.info("save some sample cats");
			Cat c = new Cat("Bobby", "curious and elegant", 5, 20.0, repo.findByName("Bengal").get(0));
			Cat c1 = new Cat("Floppy", "calm and always sleeping", 10, 50.0, repo.findByName("American Shorthair").get(0));
			Cat c2 = new Cat("Prince", "clever and lazy", 2, 500.0, repo.findByName("Bengal").get(0));

			repository.save(c);
			repository.save(c1);
			repository.save(c2);

			log.info("fetch all cats");
			for(Cat cat : repository.findAll()){
				log.info(cat.toString());
			}
		};
	}

}
