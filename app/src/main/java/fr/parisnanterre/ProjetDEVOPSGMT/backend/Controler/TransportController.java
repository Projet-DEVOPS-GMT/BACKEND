package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/transports/")
public class TransportController {
    private static final Logger logger = LoggerFactory.getLogger(TransportController.class);

    @Autowired
    private TransportService transportService;

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

            List<Transport> transports = transportService.getNonFlightTransportsByCities(depart, destination, dateDepartParsed, dateRetourParsed);


            // 🚀 Appeler Amadeus pour récupérer les vols
            String flightsJson = transportService.getFlightsFromAmadeus(depart, 200); // Ici 200€ max par vol, tu peux changer

            // 🛠 Convertir la réponse JSON en liste d'objets Transport (pour uniformiser)
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(flightsJson);
            JsonNode dataNode = root.path("data");

            if (dataNode.isArray()) {
                for (JsonNode flight : dataNode) {
                    Transport flightTransport = new Transport();
                    flightTransport.setTypeTransport("Avion"); // Vol
                    flightTransport.setVilleDepart(depart);
                    flightTransport.setVilleDestination(flight.path("destination").asText());
                    flightTransport.setDateDepart(LocalDate.parse(flight.path("departureDate").asText()));
                    flightTransport.setDateRetour(LocalDate.parse(flight.path("returnDate").asText()));
                    flightTransport.setEstimationPrix(flight.path("price").path("total").asDouble());
                    flightTransport.setTauxCO2(100.0); // ➡️ Valeur arbitraire de CO2 (à ajuster si besoin)

                    transports.add(flightTransport);
                }
            }

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
        return transport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

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
