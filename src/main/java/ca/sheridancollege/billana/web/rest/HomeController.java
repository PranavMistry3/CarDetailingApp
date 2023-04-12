package ca.sheridancollege.billana.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.services.CustomerService;

@Controller
@RequestMapping("/index")
public class HomeController {

	@Autowired
	private CustomerService customerService;

	@GetMapping
	public String addCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "index";
	}

	@PostMapping("/signIn")
	public String signInUser(@RequestParam("email") String email, Model model) {
		
		Customer customer = customerService.findCustomerByEmail(email);
		
		if (customer == null) {
			// Email does not exist, show error message to user
			System.out.println("Invalid email address");
			return "redirect:/index";
			
		} else {
			// Email exists, sign in the user
			model.addAttribute("customer", customer);
			return "profile";
		}
	}
}
