package ca.sheridancollege.billana.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.billana.domain.Appointment;
import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.Vehicle;

@Component
public class BootstrapData implements CommandLineRunner {
	
	@Autowired
    private CustomerService customerService;
	
	@Autowired
    private VehicleService vehicleService;
	
	@Autowired
	private AppointmentService appointmentService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Customer customer1 = Customer.builder()
                .name("John Smith")
                .email("john.smith@example.com")
                .phone("555-555-1234")
                .address("123 Main St, Anytown USA")
                .build();
        customerService.saveCustomer(customer1);

        Customer customer2 = Customer.builder()
                .name("Jane Doe")
                .email("jane.doe@example.com")
                .phone("555-555-5678")
                .address("456 Elm St, Anytown USA")
                .build();
        customerService.saveCustomer(customer2);

        Customer customer3 = Customer.builder()
                .name("Bob Johnson")
                .email("bob.johnson@example.com")
                .phone("555-555-9876")
                .address("789 Oak St, Anytown USA")
                .build();
        customerService.saveCustomer(customer3);
        
        Vehicle vehicle1 = Vehicle.builder()
        	    .make("Honda")
        	    .model("Civic")
        	    .vehicleYear("2018")
        	    .licensePlate("ABC123")
        	    .customer(customer1)
        	    .build();
        vehicleService.saveVehicle(vehicle1);
        
        Vehicle vehicle2 = Vehicle.builder()
        	    .make("Toyota")
        	    .model("Camry")
        	    .vehicleYear("2019")
        	    .licensePlate("XYZ789")
        	    .customer(customer2)
        	    .build();
        vehicleService.saveVehicle(vehicle2);
        
        Vehicle vehicle3 = Vehicle.builder()
        	    .make("Ford")
        	    .model("F-150")
        	    .vehicleYear("2020")
        	    .licensePlate("DEF456")
        	    .customer(customer2)
        	    .build();
        
        vehicleService.saveVehicle(vehicle3);
        
        Vehicle vehicle4 = Vehicle.builder()
        	    .make("Toyota")
        	    .model("Corolla")
        	    .vehicleYear("2020")
        	    .licensePlate("ABC123")
        	    .customer(customer3)
        	    .build();
        vehicleService.saveVehicle(vehicle4);

        	Vehicle vehicle5 = Vehicle.builder()
        	    .make("Honda")
        	    .model("Accord")
        	    .vehicleYear("2018")
        	    .licensePlate("DEF456")
        	    .customer(customer1)
        	    .build();
        	vehicleService.saveVehicle(vehicle5);

        	Vehicle vehicle6 = Vehicle.builder()
        	    .make("Ford")
        	    .model("F-150")
        	    .vehicleYear("2021")
        	    .licensePlate("GHI789")
        	    .customer(customer3)
        	    .build();
        	vehicleService.saveVehicle(vehicle6);
        	
        	Appointment appointment1 = Appointment.builder()
        	        .appointmentDateTime(LocalDateTime.of(2023, 4, 3, 10, 30))
        	        .customer(customer1)
        	        .vehicle(vehicle5)
        	        .serviceType("Oil Change")
        	        .additionalDetails("Synthetic oil preferred")
        	        .build();
        	appointmentService.saveAppointment(appointment1);
        	
        	Appointment appointment2 = Appointment.builder()
        	        .appointmentDateTime(LocalDateTime.of(2023, 4, 5, 14, 0))
        	        .customer(customer3)
        	        .vehicle(vehicle4)
        	        .serviceType("Tire Rotation")
        	        .additionalDetails("Need to balance the tires")
        	        .build();
        	appointmentService.saveAppointment(appointment2);
        	
        	Appointment appointment3 = Appointment.builder()
        	        .appointmentDateTime(LocalDateTime.of(2023, 4, 8, 9, 0))
        	        .customer(customer2)
        	        .vehicle(vehicle3)
        	        .serviceType("Brake Inspection")
        	        .additionalDetails("Hearing squeaking noise when braking")
        	        .build();
        	appointmentService.saveAppointment(appointment3);

	}

}
