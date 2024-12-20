package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.ConsommationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsommationServiceImpl implements ConsommationService {

    private final ConsommationRepository consommationRepository;

    @Autowired
    public ConsommationServiceImpl(ConsommationRepository consommationRepository) {
        this.consommationRepository = consommationRepository;
    }

    @Override
    public List<Consommation> getAllConsommations() {
        return consommationRepository.findAll();
    }

    @Override
    public Optional<Consommation> getConsommationById(Long id) {
        return consommationRepository.findById(id);
    }

    @Override
    public Consommation createConsommation(Consommation consommation) {
        return consommationRepository.save(consommation);
    }

    @Override
    public void deleteConsommation(Long id) {
        consommationRepository.deleteById(id);
    }

    @Override
    public Consommation updateConsommation(Long id, Consommation consommation) {
        Optional<Consommation> existingConsommation = consommationRepository.findById(id);
        if (existingConsommation.isPresent()) {
            Consommation updatedConsommation = existingConsommation.get();
            updatedConsommation.setType(consommation.getType());
            updatedConsommation.setPrix(consommation.getPrix());
            updatedConsommation.setTauxCO2(consommation.getTauxCO2());
            return consommationRepository.save(updatedConsommation);
        } else {
            throw new RuntimeException("Consommation not found");
        }
    }
}