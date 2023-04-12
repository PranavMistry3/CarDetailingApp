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

import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.Vehicle;
import ca.sheridancollege.billana.repositories.VehicleRepository;
import ca.sheridancollege.billana.services.CustomerService;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/add")
    public String addCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "addCustomer";
    }

    @PostMapping
    public String addCustomerSubmit(@ModelAttribute("customer") Customer customer) {
        customerService.saveCustomer(customer);
        return "redirect:/customers";
    }

    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customers";
    }
    
    @PostMapping("/customerProfile")
    public String getCustomerProfile() {
//    	customerService.saveCustomer(customer);
    	return "profile";
    }
    
    @GetMapping("/customerProfile")
    public String CustomerProfile() {
//    	customerService.saveCustomer(customer);
    	return "profile";
    }
    
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, Model model) {
        customerService.saveCustomer(customer);
        model.addAttribute("customer", customer);
        return "profile";
    }
    
    @GetMapping("/settings/{customerId}")
    public String goToSettings(@PathVariable Long customerId, Model model) {
    	model.addAttribute("customer", customerService.getCustomerById(customerId));
    	return "settings";
    }
    
    @PostMapping("/settings/save")
    public String loadSettings(Model model, @ModelAttribute("customer") Customer customer) {
    	Customer existingCustomer = customerService.findCustomerByEmail(customer.getEmail());
        if (existingCustomer != null) {
            // Update the existing customer record
            existingCustomer.setName(customer.getName());
            existingCustomer.setPhone(customer.getPhone());
            existingCustomer.setAddress(customer.getAddress());
            customerService.saveCustomer(existingCustomer);
        }
        
        model.addAttribute("customer", existingCustomer);
    	return "profile";
    }
    
    @GetMapping("/user/{customerId}")
    public String reloadProfile(@PathVariable Long customerId, Model model) {
    	Customer customer = customerService.getCustomerById(customerId);
    	model.addAttribute("customer", customer);
    	return "profile";
    }
    
    @GetMapping("/addVehicle/{customerId}")
    public String showAddVehicleForm(@PathVariable Long customerId, Model model) {
        Vehicle vehicle = new Vehicle();
        Customer customer = customerService.getCustomerById(customerId);
        vehicle.setCustomer(customer);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("customer", customer);
        model.addAttribute("allVehicles", vehicleRepository.findVehiclesByCustomerId(customerId));
        return "addVehicle";
    }
    
    @PostMapping("/addVehicle")
    public String addNewVehicle(@ModelAttribute Vehicle vehicle, Model model) {
        vehicleRepository.save(vehicle);
        model.addAttribute("customer", vehicle.getCustomer());
        Long customerId = vehicle.getCustomer().getId();
        return "redirect:/customers/addVehicle/" + customerId;
    }

}

