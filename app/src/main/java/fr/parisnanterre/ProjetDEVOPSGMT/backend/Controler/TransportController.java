package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;


import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.TransportComparisonDTO;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transports")
public class TransportController {
    
    @Autowired
    private TransportService transportService;

    @GetMapping("/compare")
    public ResponseEntity<List<TransportComparisonDTO>> compareTransports(
            @RequestParam double distance) {
        List<TransportComparisonDTO> comparisons = transportService.compareTransports(distance);
        return ResponseEntity.ok(comparisons);
    }
}
