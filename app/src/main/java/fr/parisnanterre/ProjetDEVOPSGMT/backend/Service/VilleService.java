package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;

import java.util.List;
import java.util.Optional;

public interface VilleService {
    List<Ville> getAllVilles();
    Optional<Ville> getVilleById(Long id);
    Ville createVille(Ville ville);
    Ville updateVille(Long id, Ville ville);
    void deleteVille(Long id);
}
