package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;

import java.util.List;
import java.util.Optional;

public interface ConsommationService {
    List<Consommation> getAllConsommations();
    Optional<Consommation> getConsommationById(Long id);
    Consommation createConsommation(Consommation consommation);
    Consommation updateConsommation(Long id, Consommation consommation);
    void deleteConsommation(Long id);
}
