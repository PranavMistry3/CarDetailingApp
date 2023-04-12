package ca.sheridancollege.billana.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.billana.domain.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	
	public List<Appointment> findAppointmentsByCustomerId(Long customerId);

}

