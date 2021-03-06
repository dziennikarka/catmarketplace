package fi.haagahelia.catmarketplace.web;

import fi.haagahelia.catmarketplace.domain.BreedRepository;
import fi.haagahelia.catmarketplace.domain.Cat;
import fi.haagahelia.catmarketplace.domain.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

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

    @PreAuthorize("hasAuthority('ADMIN')")
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
            dbCat.setBreed(cat.getBreed());
            repository.save(dbCat);
        }else{
             repository.save(cat);
        }
        return "redirect:/catlist";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
    public String editCat(@PathVariable("id") Long catId, Model model){
        model.addAttribute("cat", repository.findById(catId));
        model.addAttribute("breeds", breedRepository.findAll());
        return "addcat";
    }

    //RESTful methods start here
    @RequestMapping(value="/cats", method = RequestMethod.GET)
    public @ResponseBody List<Cat> catListRest(){
        return (List<Cat>) repository.findAll();
    }

    @RequestMapping(value="/cats/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Cat> findCatRest(@PathVariable("id") Long catId){
        return repository.findById(catId);
    }
    //RESTful methods end here

    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/about")
    public String about(){
        return "about";
    }
}
