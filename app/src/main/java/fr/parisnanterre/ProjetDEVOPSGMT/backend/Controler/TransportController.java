package fr.parisnanterre.ProjetDEVOPSGMT.backend.Controler;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Service.TransportService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/transports/")
public class TransportController {
    private static final Logger logger = LoggerFactory.getLogger(TransportController.class);

    @Autowired
    private TransportService transportService;

    // @GetMapping
    // public List<Transport> getAllTransports() {
    // return transportService.getAllTransports();
    // }

    // @GetMapping("/compare-transport")
    // public ResponseEntity<List<Transport>> compareTransport(
    // @RequestParam String depart,
    // @RequestParam String destination,
    // @RequestParam String dateDepart,
    // @RequestParam String dateRetour) {

    // logger.info("Requête reçue: départ = {}, destination = {}, dateDepart = {},
    // dateRetour = {}", depart, destination, dateDepart, dateRetour);
    // try {

    // LocalDate dateDepartParsed = LocalDate.parse(dateDepart);
    // LocalDate dateRetourParsed = LocalDate.parse(dateRetour);

    // List<Transport> transports = transportService.getTransportsByCities(depart,
    // destination, dateDepartParsed, dateRetourParsed);

    // if (transports.isEmpty()) {
    // return ResponseEntity.status(204).body(null);
    // }
    // return ResponseEntity.ok(transports);

    // } catch (Exception e) {
    // logger.error("Erreur lors de la récupération des transports: ", e);
    // return ResponseEntity.status(500).body(null);
    // }
    // }
    @GetMapping("/compare-transport")
    public ResponseEntity<List<Transport>> compareTransport(
            @RequestParam String depart,
            @RequestParam String destination,
            @RequestParam String dateDepart,
            @RequestParam(required = false) String dateRetour) {
        logger.info("Requête vers Amadeus: depart={}, destination={}, date={}", depart, destination, dateDepart);

        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders tokenHeaders = new HttpHeaders();
            tokenHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> tokenBody = new LinkedMultiValueMap<>();
            tokenBody.add("grant_type", "client_credentials");
            tokenBody.add("client_id", "GjrJzCIsT10VVSH3bdxYgAd37XheM4Ll");
            tokenBody.add("client_secret", "SuX2HqpfHNg1PPDR");

            HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(tokenBody, tokenHeaders);
            ResponseEntity<Map> tokenResponse = restTemplate
                    .postForEntity("https://test.api.amadeus.com/v1/security/oauth2/token", tokenRequest, Map.class);

            String accessToken = (String) tokenResponse.getBody().get("access_token");

            // === 2. Récupération des offres de vols ===
            String url = String.format(
                    "https://test.api.amadeus.com/v2/shopping/flight-offers?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s&adults=1&max=10",
                    depart, destination, dateDepart);

            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + accessToken);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<Map> flightResponse = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
            List<Map<String, Object>> data = (List<Map<String, Object>>) flightResponse.getBody().get("data");
            List<Transport> results = new ArrayList<>();

            for (Map<String, Object> flight : data) {
                Map<String, Object> price = (Map<String, Object>) flight.get("price");
                List<Map<String, Object>> itineraries = (List<Map<String, Object>>) flight.get("itineraries");

                Map<String, Object> firstItinerary = itineraries.get(0);
                String durationStr = (String) firstItinerary.get("duration");
                LocalTime duration = parseDuration(durationStr); // Utilisation de la méthode parseDuration

                // === 3. Récupération des émissions CO₂ ===
                double co2Value = 0;
                try {
                    List<Map<String, Object>> segments = (List<Map<String, Object>>) firstItinerary.get("segments");
                    if (segments != null && !segments.isEmpty()) {
                        Map<String, Object> firstSegment = segments.get(0);
                        Map<String, Object> departure = (Map<String, Object>) firstSegment.get("departure");
                        Map<String, Object> arrival = (Map<String, Object>) firstSegment.get("arrival");

                        String departureAirport = (String) departure.get("iataCode");
                        String arrivalAirport = (String) arrival.get("iataCode");

                        String co2Url = String.format(
                                "https://test.api.amadeus.com/v1/analytics/co2-emissions?originLocationCode=%s&destinationLocationCode=%s&departureDate=%s",
                                departureAirport, arrivalAirport, dateDepart);
                        HttpEntity<Void> co2Request = new HttpEntity<>(headers);
                        ResponseEntity<Map> co2Response = restTemplate.exchange(co2Url, HttpMethod.GET, co2Request,
                                Map.class);

                        if (co2Response.getBody() != null && co2Response.getBody().get("data") != null) {
                            List<Map<String, Object>> co2Data = (List<Map<String, Object>>) co2Response.getBody()
                                    .get("data");
                            if (!co2Data.isEmpty()) {
                                Map<String, Object> co2 = co2Data.get(0);
                                List<Map<String, Object>> emissions = (List<Map<String, Object>>) co2
                                        .get("co2Emissions");
                                if (emissions != null && !emissions.isEmpty()) {
                                    Map<String, Object> emission = emissions.get(0);
                                    Number weight = (Number) emission.get("weight");
                                    co2Value = weight.doubleValue();
                                }
                            }
                        }
                    }
                } catch (Exception ex) {
                    logger.warn("Erreur lors du calcul des émissions CO2 : {}", ex.getMessage());
                    co2Value = 1;
                }

                Transport t = new Transport();
                t.setTypeTransport("Avion");
                t.setEstimationPrix(Double.parseDouble((String) price.get("total")));
                t.setTauxCO2(co2Value);
                t.setVilleDepart(depart);
                t.setVilleDestination(destination);
                t.setDateDepart(LocalDate.parse(dateDepart));
                t.setDuration(duration);

                results.add(t);
            }

            return ResponseEntity.ok(results);

        } catch (Exception e) {
            logger.error("Erreur dans l'appel Amadeus", e);
            return ResponseEntity.status(500).body(null);
        }
    }

    // incorrectes
    private LocalTime parseDuration(String durationStr) {
        try {

            if (durationStr.equals("24:00")) {
                return LocalTime.of(23, 59); // Représente la durée comme 23h59m
            }

            String[] parts = durationStr.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            return LocalTime.of(hours, minutes);
        } catch (DateTimeException e) {

            logger.error("Erreur de format de durée : {}", durationStr);
            return LocalTime.of(0, 0);
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
    // return transportService.saveTransport(transport);
    // }

    // @PutMapping("/{id}")
    // public ResponseEntity<Transport> updateTransport(@PathVariable Long id,
    // @RequestBody Transport updatedTransport) {
    // Optional<Transport> existingTransport =
    // transportService.getTransportById(id);
    // if (existingTransport.isPresent()) {
    // updatedTransport.setId(id); // Assurez-vous que l'ID correspond
    // return ResponseEntity.ok(transportService.saveTransport(updatedTransport));
    // } else {
    // return ResponseEntity.notFound().build();
    // }
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
