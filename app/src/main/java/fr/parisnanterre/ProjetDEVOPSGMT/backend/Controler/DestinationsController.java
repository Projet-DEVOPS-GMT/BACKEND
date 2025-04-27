package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.DestinationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")
public class DestinationsController {

    private final DestinationsService destinationsService;

    @Autowired
    public DestinationsController(DestinationsService destinationsService) {
        this.destinationsService = destinationsService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<JsonNode>> getDestinations(
            @RequestParam String origin,
            @RequestParam String destination,
            @RequestParam double maxPrice
    ) {
        List<JsonNode> filteredDestinations = destinationsService.getDestinationsFiltered(origin, destination, maxPrice);
        return ResponseEntity.ok(filteredDestinations);
    }
}
