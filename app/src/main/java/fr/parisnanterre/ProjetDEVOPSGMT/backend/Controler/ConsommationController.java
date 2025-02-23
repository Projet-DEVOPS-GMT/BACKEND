package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.ConsommationRepository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationServiceImpl;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@RestController
@RequestMapping("/api/consommations")
public class ConsommationController {
    @Autowired
    private ConsommationServiceImpl consommationService;
    private ConsommationRepository consommationRepository;

    public ConsommationController(ConsommationServiceImpl consommationService, ConsommationRepository consommationRepository) {
        this.consommationService = consommationService;
        this.consommationRepository = consommationRepository;
    }

    @GetMapping
    public ResponseEntity<List<Consommation>> getAllConsommations() {
        System.out.println("Requête GET pour obtenir toutes les consommations reçue");
        List<Consommation> consommations = consommationService.getAllConsommations();
        return ResponseEntity.ok(consommations);
    }


    @PostMapping
    public ResponseEntity<?> enregistrerConsommation(@RequestBody Map<String, Object> consommationData) {
        String type = (String) consommationData.get("type");

        // Gestion correcte du type de "montant" (String ou Number)
        Object montantObj = consommationData.get("montant");
        Double montant = null;
        if (montantObj instanceof String) {
            montant = Double.parseDouble((String) montantObj);
        } else if (montantObj instanceof Number) {
            montant = ((Number) montantObj).doubleValue();
        }

        if (montant == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Montant invalide.");
        }

        // Créer la consommation
        Consommation consommation = new Consommation();
        consommation.setType(type);
        consommation.setMontant(montant);

        // Récupérer les autres valeurs en fonction du type de consommation
        if ("transport".equals(type)) {
            String transportType = (String) consommationData.get("transportType");
            consommation.setTransportType(transportType);
        } else if ("hebergement".equals(type)) {
            String ville = (String) consommationData.get("ville");
            consommation.setVille(ville);
        }

        // Gérer les dates si elles sont présentes
        String dateDepart = (String) consommationData.get("dateDepart");
        String dateArrivee = (String) consommationData.get("dateArrivee");
        consommation.setDateDepart(dateDepart);
        consommation.setDateArrivee(dateArrivee);

        // Créer la consommation et renvoyer la réponse via le service
        Consommation savedConsommation = consommationService.createConsommation(consommation);
    
        // Vérifier si l'enregistrement a bien eu lieu
        if (savedConsommation != null) {
            return ResponseEntity.ok(savedConsommation);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erreur lors de l'enregistrement.");
        }
    }


    // @GetMapping("/{id}")
    // public ResponseEntity<Consommation> getConsommationById(@PathVariable Long id) {

    //     Optional<Consommation> consommation = consommationService.getConsommationById(id);
    //     return consommation.map(ResponseEntity::ok)
    //                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    // }

  

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
}
