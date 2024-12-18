package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {

    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Voyage> getVoyageById(@PathVariable Long id) {
        Optional<Voyage> voyage = voyageService.getVoyageById(id);
        if (voyage.isPresent()) {
            return ResponseEntity.ok(voyage.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Voyage createVoyage(@RequestBody Voyage voyage) {
        return voyageService.saveVoyage(voyage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voyage> updateVoyage(@PathVariable Long id, @RequestBody Voyage updatedVoyage) {
        Optional<Voyage> existingVoyage = voyageService.getVoyageById(id);
        if (existingVoyage.isPresent()) {
            updatedVoyage.setId(id); 
            return ResponseEntity.ok(voyageService.saveVoyage(updatedVoyage));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) {
        Optional<Voyage> existingVoyage = voyageService.getVoyageById(id);
        if (existingVoyage.isPresent()) {
            voyageService.deleteVoyage(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
