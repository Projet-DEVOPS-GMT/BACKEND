package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Consommation;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.ConsommationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consommations")
public class ConsommationController {

    private final ConsommationService consommationService;

    @Autowired
    public ConsommationController(ConsommationService consommationService) {
        this.consommationService = consommationService;
    }


    @GetMapping
    public List<Consommation> getAllConsommations() {
        return consommationService.getAllConsommations();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Consommation> getConsommationById(@PathVariable Long id) {
        return consommationService.getConsommationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Consommation createConsommation(@RequestBody Consommation consommation) {
        return consommationService.createConsommation(consommation);
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
}
