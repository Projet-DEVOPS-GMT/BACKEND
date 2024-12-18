package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import java.util.List;

public interface VoyageService {
    List<Voyage> getAllVoyages();
    Voyage createVoyage(Voyage voyage);
    void deleteVoyage(Long id);
}