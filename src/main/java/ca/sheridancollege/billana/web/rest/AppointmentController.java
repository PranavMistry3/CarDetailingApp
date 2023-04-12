package ca.sheridancollege.billana.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ca.sheridancollege.billana.domain.Appointment;
import ca.sheridancollege.billana.domain.CarDetailingService;
import ca.sheridancollege.billana.domain.CarMake;
import ca.sheridancollege.billana.domain.CarModel;
import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.Vehicle;
import ca.sheridancollege.billana.domain.YearUtils;
import ca.sheridancollege.billana.repositories.VehicleRepository;
import ca.sheridancollege.billana.services.AppointmentService;
import ca.sheridancollege.billana.services.CustomerService;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private AppointmentService appointmentService;
	
	@Autowired
    private VehicleRepository vehicleRepository;
	
	@GetMapping("/add/{customerId}")
	public String showAddAppointmentForm(@PathVariable Long customerId, Model model) {
//	    List<Customer> customers = customerService.getAllCustomers();
	    
	    Appointment appointment = new Appointment();
	    Customer customer = customerService.getCustomerById(customerId);
	    appointment.setCustomer(customer);
	    model.addAttribute("appointment", appointment);
	    model.addAttribute("customer", customer);
	    List<Vehicle> vehicles = vehicleRepository.findVehiclesByCustomerId(customerId);
	    
	    model.addAttribute("vehicles", vehicles);
	    model.addAttribute("services", CarDetailingService.values());
	    
	    
//	    model.addAttribute("allCustomers", customers);
//	    model.addAttribute("carMakes", CarMake.values());
//	    model.addAttribute("carModels", CarModel.values());
//	    
//	    List<Vehicle> vehicles = vehicleRepository.findAll();
//	    
//	    
//	    List<Integer> years = YearUtils.getAllYears();
//        model.addAttribute("years", years);
        
	    return "addAppointment";
	}

	@GetMapping
	public String getAllAppointments(Model model) {
		model.addAttribute("appointments", appointmentService.getAllAppointments());
		return "appointments";
	}
	
	@PostMapping
	public String addAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
	    appointmentService.saveAppointment(appointment);
	    model.addAttribute("customer", appointment.getCustomer());
	    Long customerId = appointment.getCustomer().getId();
	    return "redirect:/appointments/myAppointments/" + customerId;
	}
	
	@GetMapping("/myAppointments/{customerId}")
    public String getAllAppointments(@PathVariable Long customerId, Model model) {
        List<Appointment> appointments = appointmentService.findAppointmentsByCustomerId(customerId);
//        Appointment appointment = new Appointment();
        Customer customer = customerService.getCustomerById(customerId);
//        appointment.setCustomer(customer);
        model.addAttribute("customer", customer);
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

}
