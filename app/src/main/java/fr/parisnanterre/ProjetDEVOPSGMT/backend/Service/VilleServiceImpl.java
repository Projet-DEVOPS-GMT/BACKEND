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
        return villeRepository.findAll(); // Récupère toutes les villes
    }

    @Override
    public Optional<Ville> getVilleById(Long id) {
        return villeRepository.findById(id); // Récupère une ville par son ID
    }

    @Override
    public Ville createVille(Ville ville) {
        return villeRepository.save(ville); // Crée une nouvelle ville
    }

    @Override
    public Ville updateVille(Long id, Ville ville) {
        return villeRepository.findById(id).map(existingVille -> {
            existingVille.setNom(ville.getNom());
            existingVille.setPays(ville.getPays());
            return villeRepository.save(existingVille); // Met à jour la ville existante
        }).orElseThrow(() -> new RuntimeException("Ville not found with id: " + id)); // Lève une exception si la ville n'est pas trouvée
    }

    @Override
    public void deleteVille(Long id) {
        villeRepository.deleteById(id); // Supprime la ville
    }

    @Override
    public Ville getVilleByName(String nom) {
        return villeRepository.findByNom(nom); // Recherche une ville par son nom
    }

    @Override
    public Double calculateEcoScore(Ville ville) {
        if (ville == null) {
            throw new IllegalArgumentException("La ville ne peut pas être nulle.");
        }

        Double tauxCo2 = ville.getTauxCo2() != null ? ville.getTauxCo2() : 0.0;
        Long population = ville.getPopulation() != null && ville.getPopulation() > 0 ? ville.getPopulation() : 1L;
        Double pib = ville.getPib() != null ? ville.getPib() : 0.0;

        System.out.println("Calcul de l'EcoScore pour la ville : " + ville.getNom());
        System.out.println("Taux de CO2 : " + tauxCo2);
        System.out.println("Population : " + population);
        System.out.println("PIB : " + pib);

        double score = 100.0;
        score -= tauxCo2 * 10;
        score += 10000.0 / population;
        score += pib / 1000000;

        double finalScore = Math.max(0, Math.min(score, 100));
        System.out.println("EcoScore final : " + finalScore);

        return finalScore;
    }

}