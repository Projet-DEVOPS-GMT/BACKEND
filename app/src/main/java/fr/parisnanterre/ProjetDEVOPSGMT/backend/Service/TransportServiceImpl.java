package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    // private final String SNCF_API_KEY = "cba235f446bbe269cec5fec608ead671f1c4739be57df66f5cd4cc59";
    // private final String SNCF_API_URL = "https://api.sncf.com/v1/coverage/sncf/journeys";
    
    //@Value("${sncf.api.key}")
    //private String SNCF_API_KEY;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    @Override
    public Optional<Transport> getTransportById(Long id) {
        return transportRepository.findById(id);
    }

    

    // public void saveTransportData(String jsonResponse) {
    //     try {
            
    //         ObjectMapper objectMapper = new ObjectMapper();
    //         JsonNode root = objectMapper.readTree(jsonResponse);

    //         JsonNode journeys = root.path("journeys");
    //         for (JsonNode journey : journeys) {
    //             Transport transport = new Transport();
    //             transport.setTypeTransport("Train");
    //             transport.setTauxCO2(journey.path("co2_emissions").asDouble());
    //             transport.setEstimationPrix(journey.path("price").path("amount").asDouble());

    //             transportRepository.save(transport);
    //         }
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         throw new RuntimeException("Erreur lors de la sauvegarde des données de transport.");
    //     }
    // }


    @Override
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }


     // donnee externe
    // public String getTransportData(String gareDepart, String gareArrivee) {
    //     RestTemplate restTemplate = new RestTemplate();
    //     String url = SNCF_API_URL + "?from=" + gareDepart + "&to=" + gareArrivee + "&apikey=" + SNCF_API_KEY + "&limit=50"; 
    //     return restTemplate.getForObject(url, String.class);
    // }
    // public String getTransportData(String gareDepart, String gareArrivee) {
    //     RestTemplate restTemplate = new RestTemplate();
    //     String url = SNCF_API_URL + "?from=" + gareDepart + "&to=" + gareArrivee + "&apikey=" + SNCF_API_KEY;
    //     return restTemplate.getForObject(url, String.class);
    // }
}
