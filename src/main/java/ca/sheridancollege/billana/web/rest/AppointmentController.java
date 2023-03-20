package ca.sheridancollege.billana.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.billana.domain.Appointment;
import ca.sheridancollege.billana.domain.CarDetailingService;
import ca.sheridancollege.billana.domain.CarMake;
import ca.sheridancollege.billana.domain.CarModel;
import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.YearUtils;
import ca.sheridancollege.billana.services.AppointmentService;
import ca.sheridancollege.billana.services.CustomerService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private AppointmentService appointmentService;
	
	@GetMapping("/add")
	public String showAddAppointmentForm(Model model) {
	    List<Customer> customers = customerService.getAllCustomers();
	    
	    model.addAttribute("appointment", new Appointment());
	    model.addAttribute("allCustomers", customers);
	    model.addAttribute("carMakes", CarMake.values());
	    model.addAttribute("carModels", CarModel.values());
	    model.addAttribute("services", CarDetailingService.values());
	    
	    List<Integer> years = YearUtils.getAllYears();
        model.addAttribute("years", years);
        
	    return "addAppointment";
	}

	@PostMapping
	public String addAppointment(@ModelAttribute("appointment") Appointment appointment) {
	    appointmentService.saveAppointment(appointment);
	    return "redirect:/appointments";
	}
	
	@GetMapping
    public String getAllAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

}
