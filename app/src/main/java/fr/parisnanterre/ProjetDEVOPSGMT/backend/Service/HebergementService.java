package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;

import java.util.List;
import java.util.Optional;

public interface HebergementService {
    List<Hebergement> getAllHebergements();
    Optional<Hebergement> getHebergementById(Long id);
    Hebergement saveHebergement(Hebergement hebergement);
    void deleteHebergement(Long id);
}
