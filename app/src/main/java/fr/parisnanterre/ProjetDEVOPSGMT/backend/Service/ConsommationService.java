package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;

import java.util.List;

public interface ConsommationService {
    List<Consommation> getAllConsommations();
    Consommation getConsommationById(Long id);
    Consommation saveConsommation(Consommation consommation);
    Consommation updateConsommation(Long id, Consommation consommation);
    void deleteConsommation(Long id);
}
