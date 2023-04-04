package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
