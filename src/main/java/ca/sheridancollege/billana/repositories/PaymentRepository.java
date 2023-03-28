package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
