package ca.sheridancollege.billana.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.sheridancollege.billana.domain.Vehicle;
import ca.sheridancollege.billana.repositories.VehicleRepository;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public void saveVehicle(Vehicle vehicle) {
		vehicleRepository.save(vehicle);
	}

}
