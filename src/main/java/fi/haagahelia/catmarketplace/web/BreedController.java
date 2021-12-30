package fi.haagahelia.catmarketplace.web;

import fi.haagahelia.catmarketplace.domain.Breed;
import fi.haagahelia.catmarketplace.domain.BreedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BreedController {
    @Autowired
    private BreedRepository repository;

    @RequestMapping(value="/breeds", method = RequestMethod.GET)
    public String breedList(Model model){
        model.addAttribute("breeds", repository.findAll());
        return "breedlist";
    }

    @RequestMapping(value="/addbreed", method = RequestMethod.GET)
    public String addBreed(Model model){
        model.addAttribute("breed", new Breed());
        return "addbreed";
    }

    @RequestMapping(value="/savebreed", method = RequestMethod.POST)
    public String saveBreed(Breed breed){
        repository.save(breed);
        return "redirect:/breeds";
    }

}
