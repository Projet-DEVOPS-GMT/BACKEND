package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.PaysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pays")
public class PaysController {

    private final PaysService paysService;

    @Autowired
    public PaysController(PaysService paysService) {
        this.paysService = paysService;
    }


    @GetMapping
    public List<Pays> getAllPays() {
        return paysService.getAllPays();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pays> getPaysById(@PathVariable Long id) {
        return paysService.getPaysById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pays createPays(@RequestBody Pays pays) {
        return paysService.createPays(pays);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pays> updatePays(@PathVariable Long id, @RequestBody Pays pays) {
        try {
            Pays updatedPays = paysService.updatePays(id, pays);
            return ResponseEntity.ok(updatedPays);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePays(@PathVariable Long id) {
        paysService.deletePays(id);
        return ResponseEntity.noContent().build();
    }
}
