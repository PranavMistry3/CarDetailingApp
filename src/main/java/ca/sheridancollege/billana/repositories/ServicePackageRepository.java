package ca.sheridancollege.billana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.sheridancollege.billana.domain.ServicePackage;

public interface ServicePackageRepository extends JpaRepository<ServicePackage, Long> {

}
