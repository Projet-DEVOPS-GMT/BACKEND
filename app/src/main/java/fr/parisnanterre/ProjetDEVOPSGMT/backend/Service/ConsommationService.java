package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ConsommationService {
    List<Consommation> getAllConsommations();
    Optional<Consommation> getConsommationById(Long id);
    Consommation createConsommation(Consommation consommation);

    // BigDecimal tauxCO2transport(Map<String, String> consommation);
    // BigDecimal tauxCO2hebergement(Map<String, String> consommation);
    BigDecimal tauxCO2restauration(Map<String, String> consommation);

    Consommation updateConsommation(Long id, Consommation consommation);
    void deleteConsommation(Long id);
}
