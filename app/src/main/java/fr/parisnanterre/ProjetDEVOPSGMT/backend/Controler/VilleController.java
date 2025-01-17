package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/villes")
public class VilleController {

    private final VilleService villeService;

    @Autowired
    public VilleController(VilleService villeService) {
        this.villeService = villeService;
    }

    @GetMapping
    public List<Ville> getAllVilles() {
        return villeService.getAllVilles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ville> getVilleById(@PathVariable Long id) {
        return villeService.getVilleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/eco-score")
    public ResponseEntity<?> getEcoScore(@PathVariable Long id) {
        Optional<Ville> villeOpt = villeService.getVilleById(id);
        if (villeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Ville ville = villeOpt.get();
        double ecoScore = villeService.calculateEcoScore(ville);

        return ResponseEntity.ok().body(new Object() {
            public final String city = ville.getNom();
            public final double score = ecoScore;
            public final Ville details = ville;
        });
    }

    @PostMapping
    public Ville createVille(@RequestBody Ville ville) {
        return villeService.createVille(ville);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ville> updateVille(@PathVariable Long id, @RequestBody Ville ville) {
        try {
            Ville updatedVille = villeService.updateVille(id, ville);
            return ResponseEntity.ok(updatedVille);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVille(@PathVariable Long id) {
        villeService.deleteVille(id);
        return ResponseEntity.noContent().build();
    }
}
