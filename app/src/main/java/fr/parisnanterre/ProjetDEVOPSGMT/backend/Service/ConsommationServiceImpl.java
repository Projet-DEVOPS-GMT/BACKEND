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
    public Consommation updateConsommation(Long id, Consommation consommation) {
        return consommationRepository.findById(id).map(existingConsommation -> {
            existingConsommation.setType(consommation.getType());
            existingConsommation.setPrix(consommation.getPrix());
            existingConsommation.setTauxCO2(consommation.getTauxCO2());
            existingConsommation.setTransport(consommation.getTransport());
            existingConsommation.setHebergement(consommation.getHebergement());
            existingConsommation.setRestauration(consommation.getRestauration());
            existingConsommation.setVoyage(consommation.getVoyage());
            return consommationRepository.save(existingConsommation);
        }).orElseThrow(() -> new RuntimeException("Consommation not found with id: " + id));
    }

    @Override
    public void deleteConsommation(Long id) {
        consommationRepository.deleteById(id);
    }
}
