package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
