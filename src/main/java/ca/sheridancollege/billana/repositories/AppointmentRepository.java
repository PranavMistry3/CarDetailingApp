package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.billana.domain.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}

