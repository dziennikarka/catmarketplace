package fi.haagahelia.catmarketplace.web;

import fi.haagahelia.catmarketplace.domain.BreedRepository;
import fi.haagahelia.catmarketplace.domain.Cat;
import fi.haagahelia.catmarketplace.domain.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CatController {
    @Autowired
    private CatRepository repository;

    @Autowired
    private BreedRepository breedRepository;

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public String getCat(){
        return "cats";
    }

    @RequestMapping(value="/catlist", method = RequestMethod.GET)
    public String catList(Model model){
        model.addAttribute("cats", repository.findAll());
        return "catlist";
    }

    @RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
    public String deleteCat(@PathVariable("id") Long catId, Model model){
        repository.deleteById(catId);
        return "redirect:/catlist";
    }

    @RequestMapping(value="/add")
    public String addCat(Model model){
        model.addAttribute("cat", new Cat());
        model.addAttribute("breeds", breedRepository.findAll());
        return "addcat";
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String saveCat(Cat cat){
        if(cat.getCatId() != null){
            Cat dbCat = repository.findById(cat.getCatId()).get();
            dbCat.setName(cat.getName());
            dbCat.setDescription(cat.getDescription());
            dbCat.setAge(cat.getAge());
            dbCat.setPrice(cat.getPrice());
            repository.save(dbCat);
        }else{
             repository.save(cat);
        }
        return "redirect:/catlist";
    }

    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editCat(@PathVariable("id") Long catId, Model model){
        model.addAttribute("cat", repository.findById(catId));
        model.addAttribute("breeds", breedRepository.findAll());
        return "addcat";
    }
}
