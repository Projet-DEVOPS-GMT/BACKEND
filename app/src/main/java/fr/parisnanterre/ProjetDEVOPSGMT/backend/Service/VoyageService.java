package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;

import java.util.List;
import java.util.Optional;

public interface VoyageService {
    List<Voyage> getAllVoyages();
    Optional<Voyage> getVoyageById(Long id);
    Voyage saveVoyage(Voyage voyage);
    void deleteVoyage(Long id);
}
