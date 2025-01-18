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
}