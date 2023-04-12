package ca.sheridancollege.billana.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.billana.repositories.VehicleRepository;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {
	
	@Autowired
    private VehicleRepository vehicleRepository;
	
	


}
