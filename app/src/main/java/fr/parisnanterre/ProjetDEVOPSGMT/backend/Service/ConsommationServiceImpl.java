package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.ConsommationRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ConsommationServiceImpl implements ConsommationService {

    @Autowired
    private ConsommationRepository consommationRepository;

    @Override
    public List<Consommation> getAllConsommations() {
        return consommationRepository.findAll();
    }

    @Override
    public Consommation getConsommationById(Long id) {
        return consommationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consommation introuvable avec l'ID : " + id));
    }

    @Override
    public Consommation saveConsommation(Consommation consommation) {
        return consommationRepository.save(consommation);
    }

    @Override
    public Consommation updateConsommation(Long id, Consommation consommation) {
        Consommation existingConsommation = getConsommationById(id);
        existingConsommation.setType(consommation.getType());
        existingConsommation.setPrix(consommation.getPrix());
        existingConsommation.setTauxCO2(consommation.getTauxCO2());
        existingConsommation.setTransport(consommation.getTransport());
        existingConsommation.setHebergement(consommation.getHebergement());
        existingConsommation.setRestauration(consommation.getRestauration());
        existingConsommation.setVoyage(consommation.getVoyage());
        return consommationRepository.save(existingConsommation);
    }

    @Override
    public void deleteConsommation(Long id) {
        consommationRepository.deleteById(id);
    }
}
 