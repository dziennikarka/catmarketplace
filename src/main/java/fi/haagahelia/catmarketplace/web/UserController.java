package fi.haagahelia.catmarketplace.web;

import fi.haagahelia.catmarketplace.domain.SignupForm;
import fi.haagahelia.catmarketplace.domain.User;
import fi.haagahelia.catmarketplace.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/signup")
    public String addUser(Model model){
        model.addAttribute("signupform", new SignupForm());
        return "signup";
    }

    @RequestMapping(value="/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid @ModelAttribute ("signupform") SignupForm signupForm, BindingResult bindingResult){
        if (!bindingResult.hasErrors()) { // validation errors
    		if (signupForm.getPassword().equals(signupForm.getPasswordCheck())) { // check password match
	    		String pwd = signupForm.getPassword();
		    	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
		    	String hashPwd = bc.encode(pwd);

		    	User newUser = new User();
		    	newUser.setPasswordHash(hashPwd);
		    	newUser.setUsername(signupForm.getUsername());
		    	newUser.setRole("USER");
                newUser.setEmail(signupForm.getEmail());
		    	if (userRepository.findByUsername(signupForm.getUsername()) == null) {
		    		userRepository.save(newUser);
		    	}
		    	else {
	    			bindingResult.rejectValue("username", "err.username", "Username already exists");
	    			return "signup";
		    	}
    		}
    		else {
    			bindingResult.rejectValue("passwordCheck", "err.passCheck", "Passwords does not match");
    			return "signup";
    		}
    	}
    	else {
    		return "signup";
    	}
    	return "redirect:/login";
    }
}
