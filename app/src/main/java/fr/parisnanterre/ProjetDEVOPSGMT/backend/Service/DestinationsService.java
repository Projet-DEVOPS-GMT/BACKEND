package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import java.util.Map;
import java.util.HashMap;
import java.time.Instant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DestinationsService {

    private static final String AMADEUS_API_URL = "https://test.api.amadeus.com/v1/shopping/flight-destinations";
    private static final String CLIENT_ID = "w33Owm5XeYfUV7VmSGQ86rv20ezWgMCS";
    private static final String CLIENT_SECRET = "cIMWgwPw2zvGJ0Aw";

    private String accessToken;
    private Instant tokenExpiration;  // Expiration du token

    public String getDestinations(String origin, double maxPrice) {
        String token = getAccessToken();
        String url = AMADEUS_API_URL + "?origin=" + origin + "&maxPrice=" + maxPrice;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    private String getAccessToken() {
        // Si token déjà présent et non expiré, le réutiliser
        if (accessToken != null && tokenExpiration != null && Instant.now().isBefore(tokenExpiration)) {
            return accessToken;
        }

        String tokenUrl = "https://test.api.amadeus.com/v1/security/oauth2/token";

        RestTemplate restTemplate = new RestTemplate();
        String body = "grant_type=client_credentials"
                    + "&client_id=" + CLIENT_ID
                    + "&client_secret=" + CLIENT_SECRET;

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, request, Map.class);

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                accessToken = (String) response.getBody().get("access_token");
                Integer expiresIn = (Integer) response.getBody().get("expires_in");

                // Définir la date d'expiration du token
                tokenExpiration = Instant.now().plusSeconds(expiresIn - 60); // 1 min de marge

                return accessToken;
            } else {
                throw new RuntimeException("Impossible de récupérer le token d'accès Amadeus");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du token Amadeus", e);
        }
    }
}
