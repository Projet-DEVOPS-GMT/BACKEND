package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.ConsommationRepository;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConsommationServiceImpl {

    @Autowired
    private ConsommationRepository consommationRepository;

    public Consommation createConsommation(Consommation consommation) {
        return consommationRepository.save(consommation);
    }
    public List<Consommation> getAllConsommations() {
        return consommationRepository.findAll();
    }
    
    
    public Optional<Consommation> getConsommationById(Long id) {
        return consommationRepository.findById(id);
    }

    public Consommation updateConsommation(Long id, Consommation consommation) {
        if (consommationRepository.existsById(id)) {
            consommation.setId(id);
            return consommationRepository.save(consommation);
        }
        return null;
    }

    public void deleteConsommation(Long id) {
        consommationRepository.deleteById(id);
    }
}
