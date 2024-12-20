package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/consommations")
public class ConsommationController {

    @Autowired
    private ConsommationService consommationService;


    @GetMapping
    public List<Consommation> getAllConsommations() {
        return consommationService.getAllConsommations();
    }

    @PostMapping
    public ResponseEntity<Consommation> createConsommation(@RequestBody Consommation consommation) {
 
        BigDecimal co2 = calculerCO2(consommation);
        consommation.setTauxCO2(co2);
        Consommation savedConsommation = consommationService.createConsommation(consommation);
        return ResponseEntity.ok(savedConsommation);
    }

  
    @GetMapping("/{id}")
    public ResponseEntity<Consommation> getConsommationById(@PathVariable Long id) {
        return consommationService.getConsommationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<Consommation> updateConsommation(@PathVariable Long id, @RequestBody Consommation consommation) {
        try {
            Consommation updatedConsommation = consommationService.updateConsommation(id, consommation);
            return ResponseEntity.ok(updatedConsommation);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsommation(@PathVariable Long id) {
        consommationService.deleteConsommation(id);
        return ResponseEntity.noContent().build();
    }


    private BigDecimal calculerCO2(Consommation consommation) {
        BigDecimal co2 = BigDecimal.ZERO;

        // Calcul CO₂ pour les transports
        if ("Transport".equals(consommation.getType())) {
            if (consommation.getTransport() != null) {
                double distance = calculerDistance(consommation.getTransport().getVilleDepart(), consommation.getTransport().getVilleDestination());
                switch (consommation.getTransport().getTypeTransport()) {
                    case "Avion":
                        co2 = BigDecimal.valueOf(0.25 * distance); // En kg de CO₂ par km pour un vol
                        break;
                    case "Train":
                        co2 = BigDecimal.valueOf(0.041 * distance); // En kg de CO₂ par km pour un train
                        break;
                    case "Bus":
                        co2 = BigDecimal.valueOf(0.01 * distance); // En kg de CO₂ par km pour un bus
                        break;
                }
            }
        }
        // Calcul CO₂ pour l'hébergement
        else if ("Hébergement".equals(consommation.getType())) {
            co2 = BigDecimal.valueOf(15 * consommation.getPrix().doubleValue()); // Estimation par nuit d'hôtel
        }
        // Calcul CO₂ pour la restauration
        else if ("Restauration".equals(consommation.getType())) {
            co2 = BigDecimal.valueOf(3 * consommation.getPrix().doubleValue()); // Estimation par repas
        }

        return co2;
    }

    // fonction pour estimer la distance entre deux villes (on va améliorer)
    private double calculerDistance(String depart, String destination) {
        // Pour l'instant, renvoyons une distance fictive
        return 1000;  
    }
}