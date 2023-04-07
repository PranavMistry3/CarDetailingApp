package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
