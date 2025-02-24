package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.HebergementService;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hebergements")
public class HebergementController {

    private final HebergementService hebergementService;
    private final VilleService villeService;

    @Autowired
    public HebergementController(HebergementService hebergementService, VilleService villeService) {
        this.hebergementService = hebergementService;
        this.villeService = villeService;
    }

    @GetMapping
    public List<Hebergement> getAllHebergements() {
        return hebergementService.getAllHebergements();
    }

    @GetMapping("/search")
    public ResponseEntity<?> getHebergementsByCriteria(
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) String typeHebergement) {

        Ville villeObj = null;
        if (ville != null && !ville.isEmpty()) {
            villeObj = villeService.getVilleByName(ville);
        }

        if (ville != null && villeObj == null) {
            return ResponseEntity.ok("Aucun hébergement trouvé pour cette ville.");
        }

        List<Hebergement> result;
        if (villeObj != null && typeHebergement != null) {
            result = hebergementService.getHebergementsByCriteria(villeObj, typeHebergement);
        } else if (villeObj != null) {
            result = hebergementService.getHebergementsByCriteria(villeObj, null);
        } else if (typeHebergement != null) {
            result = hebergementService.getHebergementsByCriteria(null, typeHebergement);
        } else {
            result = hebergementService.getAllHebergements();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Hebergement> getHebergementById(@PathVariable Long id) {
        Optional<Hebergement> hebergement = hebergementService.getHebergementById(id);
        return hebergement.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Hebergement createHebergement(@RequestBody Hebergement hebergement) {
        return hebergementService.saveHebergement(hebergement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Hebergement> updateHebergement(@PathVariable Long id,
            @RequestBody Hebergement hebergementDetails) {
        Optional<Hebergement> hebergementOptional = hebergementService.getHebergementById(id);

        if (hebergementOptional.isPresent()) {
            Hebergement hebergement = hebergementOptional.get();
            hebergement.setTypeHebergement(hebergementDetails.getTypeHebergement());
            hebergement.setPhoto(hebergementDetails.getPhoto());
            hebergement.setTauxCO2(hebergementDetails.getTauxCO2());
            hebergement.setDescription(hebergementDetails.getDescription());
            hebergement.setEquipement(hebergementDetails.getEquipement());
            hebergement.setConditionsReservation(hebergementDetails.getConditionsReservation());

            Hebergement updatedHebergement = hebergementService.saveHebergement(hebergement);
            return ResponseEntity.ok(updatedHebergement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHebergement(@PathVariable Long id) {
        if (hebergementService.getHebergementById(id).isPresent()) {
            hebergementService.deleteHebergement(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/types")
    public ResponseEntity<List<String>> getAllTypeHebergements() {
        List<String> types = hebergementService.getAllTypeHebergements();
        if (types.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(types);
    }

}
