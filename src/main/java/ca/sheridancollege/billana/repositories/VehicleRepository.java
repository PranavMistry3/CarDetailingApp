package ca.sheridancollege.billana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.billana.domain.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	
	public List<Vehicle> findVehiclesByCustomerId(Long id);

}
