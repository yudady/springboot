package tk.tommy.controller;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.tommy.service.Animal;

@Controller
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";


	@Autowired
    Set<Animal> animals;

	private Animal getAnimal(String name){
	    return animals.stream().filter(e -> e.accept(name)).findFirst().get();
    }

	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
        Animal animal1 = getAnimal("Dog");
        Animal animal2 = getAnimal("Cat");
        animal1.action();
        animal2.action();
		model.put("message", this.message);
		return "welcome";
	}

}