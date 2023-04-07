package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
