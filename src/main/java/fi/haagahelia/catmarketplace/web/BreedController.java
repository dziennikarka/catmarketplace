package fi.haagahelia.catmarketplace.web;

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

}
