package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VilleServiceImpl implements VilleService {

    private final VilleRepository villeRepository;

    @Autowired
    public VilleServiceImpl(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    @Override
    public List<Ville> getAllVilles() {
        return villeRepository.findAll();
    }

    @Override
    public Optional<Ville> getVilleById(Long id) {
        return villeRepository.findById(id);
    }

    @Override
    public Ville createVille(Ville ville) {
        return villeRepository.save(ville);
    }

    @Override
    public Ville updateVille(Long id, Ville ville) {
        return villeRepository.findById(id).map(existingVille -> {
            existingVille.setNom(ville.getNom());
            existingVille.setPays(ville.getPays());
            return villeRepository.save(existingVille);
        }).orElseThrow(() -> new RuntimeException("Ville not found with id: " + id));
    }

    @Override
    public void deleteVille(Long id) {
        villeRepository.deleteById(id);
    }

    @Override
    public Double calculateEcoScore(Ville ville) {
        // Logique métier simplifiée : réduire le score si le CO2 est élevé, augmenter si PIB/population est faible
        double score = 100.0; // Base
        score -= ville.getTauxCo2() * 10; // Réduction proportionnelle au CO₂
        score += 10000.0 / ville.getPopulation(); // Augmentation pour petites villes
        score += ville.getPib() / 1000000; // Contribution du PIB (en millions)
        return Math.max(0, Math.min(score, 100)); // Score entre 0 et 100
    }
}
