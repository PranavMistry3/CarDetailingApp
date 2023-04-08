package ca.sheridancollege.billana.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.sheridancollege.billana.repositories.ServicePackageRepository;

@Service
public class ServiceService {

    @Autowired
    private ServicePackageRepository servicePackageRepository;

    @Transactional(readOnly = true)
    public List<ca.sheridancollege.billana.domain.ServicePackage> getAllServices() {
        return servicePackageRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<ca.sheridancollege.billana.domain.ServicePackage> getServiceById(Long id) {
        return servicePackageRepository.findById(id);
    }

    @Transactional
    public ca.sheridancollege.billana.domain.ServicePackage saveService(ca.sheridancollege.billana.domain.ServicePackage servicePackage) {
        return servicePackageRepository.save(servicePackage);
    }

    @Transactional
    public void deleteService(Long id) {
    	servicePackageRepository.deleteById(id);
    }

}

