package ca.sheridancollege.billana.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.billana.domain.ServicePackage;
import ca.sheridancollege.billana.services.ServiceService;

@Controller
@RequestMapping("/services")
public class ServiceController {

	@Autowired
	private ServiceService serviceService;
	
	@GetMapping
    public String getAllServices(Model model) {
		List<ServicePackage> servicePackages = serviceService.getAllServices();
		model.addAttribute("services", servicePackages);
        return "services";
	}
}
