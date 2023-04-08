package ca.sheridancollege.billana.bootstrap;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ca.sheridancollege.billana.domain.Appointment;
import ca.sheridancollege.billana.domain.CarMake;
import ca.sheridancollege.billana.domain.CarModel;
import ca.sheridancollege.billana.domain.Customer;
import ca.sheridancollege.billana.domain.Detailer;
import ca.sheridancollege.billana.domain.Payment;
import ca.sheridancollege.billana.domain.ServicePackage;
import ca.sheridancollege.billana.domain.Vehicle;
import ca.sheridancollege.billana.repositories.AppointmentRepository;
import ca.sheridancollege.billana.repositories.CustomerRepository;
import ca.sheridancollege.billana.repositories.DetailerRepository;
import ca.sheridancollege.billana.repositories.PaymentRepository;
import ca.sheridancollege.billana.repositories.ServicePackageRepository;
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
	private ServicePackageRepository servicePackageRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Customer customer1 = Customer.builder().email("jonsnow@gmail.com").name("Aegon Targaryen").phone("6655425678").address("1 Wall St, Wall")
				.vehicles(new ArrayList<Vehicle>()).payments(new ArrayList<Payment>())
				.appointments(new ArrayList<Appointment>()).build();
		
		Vehicle vehicle1 = Vehicle.builder().model("Elantra").make("Hundai").vehicle_number("MAAE865").model_year(2022).customer(new Customer()).build();
		
		Detailer detailer1 = Detailer.builder().name("Matrix").location("Toronto").build();
		
		ServicePackage service1 = ServicePackage.builder().service_type("Express Detail").description("Vacuum Interior, Wash Rubber Floor Mats, Detail Interior (dash, console, air vents etc.), Clean Windows Inside and Out, Clean Door Jambs, Air Freshener, Wash Exterior, Dress Tires, Clean Wheels, Rims & Wheel Wells, Chamois Dry")
				.price("79.65").build();
		ServicePackage service2 = ServicePackage.builder().service_type("Carpet Shampoo").description("Vacuum Interior, Wash Rubber Floor Mats, Detail Interior (dash, console, air vents etc.), Shampoo Carpets and Floor Mats, Clean Windows Inside and Out, Clean Door Jambs, Air Freshener, Wash Exterior, Dress Tires")
				.price("139.82").build();
		ServicePackage service3 = ServicePackage.builder().service_type("Seat Shampoo").description("Vacuum Interior, Wash Rubber Floor Mats, Detail Interior (dash, console, air vents etc.), Shampoo Seats, Clean Windows Inside and Out, Clean Door Jambs, Air Freshener, Wash Exterior, Dress Tires, Clean Wheels, Rims & Wheel Wells, Chamois Dry")
				.price("139.82").build();
		ServicePackage service4 = ServicePackage.builder().service_type("Interior Shampoo").description("Wash Exterior, Vacuum Interior, Wash Rubber Floor Mats, Detail Interior, Chamois Dry")
				.price("179.87").build();
		ServicePackage service5 = ServicePackage.builder().service_type("Express Wax").description("Wash Exterior, Chamois Dry, Hand Apply One Coat of Wax")
				.price("87.17").build();
		
		Appointment appointment1 = Appointment.builder().servicePackage(new ServicePackage()).appointmentDateTime(LocalDateTime.parse("2023-02-16T20:55"))
				.carMake(CarMake.AUDI.toString()).carModel(CarModel.A4.toString()).yearMade("1990")
				.vechicle(new Vehicle()).detailer(new Detailer()).customer(new Customer()).build();
		
		customer1.getVehicles().add(vehicle1);
		vehicle1.setCustomer(customer1);
		appointment1.setCustomer(customer1);
		appointment1.setVechicle(vehicle1);
		appointment1.setDetailer(detailer1);
		appointment1.setServicePackage(service1);
		
		customer1 = customerRepository.save(customer1);
		vehicle1 = vehicleRepository.save(vehicle1);
		detailer1 = detailerRepository.save(detailer1);
		service1 = servicePackageRepository.save(service1);
		appointment1 = appointmentRepository.save(appointment1);
		
		service2 = servicePackageRepository.save(service2);
		service3 = servicePackageRepository.save(service3);
		service4 = servicePackageRepository.save(service4);
		service5 = servicePackageRepository.save(service5);
		
		
	}

}
