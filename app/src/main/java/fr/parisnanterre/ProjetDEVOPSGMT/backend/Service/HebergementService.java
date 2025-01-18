package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;

import java.util.List;
import java.util.Optional;

public interface HebergementService {
    List<Hebergement> getAllHebergements();
    List<Hebergement> getHebergementsByCriteria(Ville ville, String typeHebergement); // Mise à jour ici
    Optional<Hebergement> getHebergementById(Long id);
    Hebergement saveHebergement(Hebergement hebergement);
    void deleteHebergement(Long id);
}
