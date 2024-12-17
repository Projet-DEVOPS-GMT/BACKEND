package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controller;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hebergements")
public class HebergementController {

    private final HebergementService hebergementService;

    @Autowired
    public HebergementController(HebergementService hebergementService) {
        this.hebergementService = hebergementService;
    }

    // Récupérer tous les hébergements
    @GetMapping
    public List<Hebergement> getAllHebergements() {
        return hebergementService.getAllHebergements();
    }

    // Récupérer un hébergement par ID
    @GetMapping("/{id}")
    public ResponseEntity<Hebergement> getHebergementById(@PathVariable Long id) {
        Optional<Hebergement> hebergement = hebergementService.getHebergementById(id);
        return hebergement.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Créer un nouvel hébergement
    @PostMapping
    public Hebergement createHebergement(@RequestBody Hebergement hebergement) {
        return hebergementService.saveHebergement(hebergement);
    }

    // Mettre à jour un hébergement existant
    @PutMapping("/{id}")
    public ResponseEntity<Hebergement> updateHebergement(@PathVariable Long id, @RequestBody Hebergement hebergementDetails) {
        Optional<Hebergement> hebergementOptional = hebergementService.getHebergementById(id);

        if (hebergementOptional.isPresent()) {
            Hebergement hebergement = hebergementOptional.get();
            hebergement.setTypeHebergement(hebergementDetails.getTypeHebergement());
            hebergement.setPrix(hebergementDetails.getPrix());
            hebergement.setTauxCO2(hebergementDetails.getTauxCO2());
            hebergement.setDescription(hebergementDetails.getDescription());
            hebergement.setEquipement(hebergementDetails.getEquipement());
            hebergement.setPhoto(hebergementDetails.getPhoto());
            hebergement.setCondition(hebergementDetails.getCondition());

            Hebergement updatedHebergement = hebergementService.saveHebergement(hebergement);
            return ResponseEntity.ok(updatedHebergement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un hébergement par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHebergement(@PathVariable Long id) {
        if (hebergementService.getHebergementById(id).isPresent()) {
            hebergementService.deleteHebergement(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
