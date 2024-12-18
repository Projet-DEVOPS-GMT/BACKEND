package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;



import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.VoyageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voyages")
public class VoyageController {
    
    @Autowired
    private VoyageService voyageService;

    @GetMapping
    public List<Voyage> getAllVoyages() {
        return voyageService.getAllVoyages();
    }

    @PostMapping
    public Voyage createVoyage(@RequestBody Voyage voyage) {
        return voyageService.createVoyage(voyage);
    }

	@DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteVoyage(@PathVariable Long id) { 
            voyageService.deleteVoyage(id); return ResponseEntity.noContent().build(); 
    }
}
