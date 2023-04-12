package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.billana.domain.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	public Customer findByEmail(String email);

}

