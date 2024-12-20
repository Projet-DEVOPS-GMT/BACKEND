package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/transports/")
public class TransportController {
    private static final Logger logger = LoggerFactory.getLogger(TransportController.class);

    @Autowired
    private TransportService transportService;

    // @GetMapping
    // public List<Transport> getAllTransports() {
    //     return transportService.getAllTransports();
    // }

   @GetMapping("/compare-transport")
    public ResponseEntity<List<Transport>> compareTransport(
            @RequestParam String depart,
            @RequestParam String destination,
            @RequestParam String dateDepart,  
            @RequestParam String dateRetour) { 
        
        logger.info("Requête reçue: départ = {}, destination = {}, dateDepart = {}, dateRetour = {}", depart, destination, dateDepart, dateRetour);
        try {
 
            LocalDate dateDepartParsed = LocalDate.parse(dateDepart);
            LocalDate dateRetourParsed = LocalDate.parse(dateRetour);

            List<Transport> transports = transportService.getTransportsByCities(depart, destination, dateDepartParsed, dateRetourParsed);

            if (transports.isEmpty()) {
                return ResponseEntity.status(204).body(null);  
            }
            return ResponseEntity.ok(transports);

        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des transports: ", e);
            return ResponseEntity.status(500).body(null);  
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transport> getTransportById(@PathVariable Long id) {
        Optional<Transport> transport = transportService.getTransportById(id);
        if (transport.isPresent()) {
            return ResponseEntity.ok(transport.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // @PostMapping
    // public Transport createTransport(@RequestBody Transport transport) {
    //     return transportService.saveTransport(transport);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Transport> updateTransport(@PathVariable Long id, @RequestBody Transport updatedTransport) {
    //     Optional<Transport> existingTransport = transportService.getTransportById(id);
    //     if (existingTransport.isPresent()) {
    //         updatedTransport.setId(id); // Assurez-vous que l'ID correspond
    //         return ResponseEntity.ok(transportService.saveTransport(updatedTransport));
    //     } else {
    //         return ResponseEntity.notFound().build();
    //     }
    // }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransport(@PathVariable Long id) {
        Optional<Transport> existingTransport = transportService.getTransportById(id);
        if (existingTransport.isPresent()) {
            transportService.deleteTransport(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
