package ca.sheridancollege.billana.bootstrap;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.billana.domain.Appointment;
import ca.sheridancollege.billana.domain.CarMake;
import ca.sheridancollege.billana.domain.CarModel;
import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.Detailer;
import ca.sheridancollege.billana.domain.Payment;
import ca.sheridancollege.billana.domain.Vehicle;
import ca.sheridancollege.billana.repositories.AppointmentRepository;
import ca.sheridancollege.billana.repositories.CustomerRepository;
import ca.sheridancollege.billana.repositories.DetailerRepository;
import ca.sheridancollege.billana.repositories.PaymentRepository;
import ca.sheridancollege.billana.repositories.VehicleRepository;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BootstrapAppointmentData implements CommandLineRunner {

	private AppointmentRepository appointmentRepository;
	private CustomerRepository customerRepository;
	private DetailerRepository detailerRepository;
	private VehicleRepository vehicleRepository;
	private PaymentRepository paymentRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Customer customer1 = Customer.builder().email("jonsnow@gmail.com").name("Aegon Targaryen").phone("6655425678").address("1 Wall St, Wall")
				.vehicles(new ArrayList<Vehicle>()).payments(new ArrayList<Payment>())
				.appointments(new ArrayList<Appointment>()).build();
		
		Vehicle vehicle1 = Vehicle.builder().model("Elantra").make("Hundai").vehicle_number("MAAE865").model_year(2022).customer(new Customer()).build();
		
		Detailer detailer1 = Detailer.builder().name("Matrix").location("Toronto").build();
		
		Appointment appointment1 = Appointment.builder().serviceType("ABC").appointmentDateTime(LocalDateTime.parse("2023-02-16T20:55"))
				.carMake(CarMake.AUDI.toString()).carModel(CarModel.A4.toString()).yearMade("1990")
				.vechicle(new Vehicle()).detailer(new Detailer()).customer(new Customer()).build();
		
		customer1.getVehicles().add(vehicle1);
		vehicle1.setCustomer(customer1);
		appointment1.setCustomer(customer1);
		appointment1.setVechicle(vehicle1);
		appointment1.setDetailer(detailer1);
		
		customer1 = customerRepository.save(customer1);
		vehicle1 = vehicleRepository.save(vehicle1);
		detailer1 = detailerRepository.save(detailer1);
		appointment1 = appointmentRepository.save(appointment1);
		
		
	}

}
