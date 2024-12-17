package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.RestaurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurations")
public class RestaurationController {

    private final RestaurationService restaurationService;

    @Autowired
    public RestaurationController(RestaurationService restaurationService) {
        this.restaurationService = restaurationService;
    }


    @GetMapping
    public List<Restauration> getAllRestaurations() {
        return restaurationService.getAllRestaurations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restauration> getRestaurationById(@PathVariable Long id) {
        return restaurationService.getRestaurationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Restauration createRestauration(@RequestBody Restauration restauration) {
        return restaurationService.createRestauration(restauration);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Restauration> updateRestauration(@PathVariable Long id, @RequestBody Restauration restauration) {
        try {
            Restauration updatedRestauration = restaurationService.updateRestauration(id, restauration);
            return ResponseEntity.ok(updatedRestauration);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestauration(@PathVariable Long id) {
        restaurationService.deleteRestauration(id);
        return ResponseEntity.noContent().build();
    }
}
