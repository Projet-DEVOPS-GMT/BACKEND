package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransportService {
    List<Transport> getAllTransports();
    Optional<Transport> getTransportById(Long id);
    void deleteTransport(Long id);

    List<Transport> getNonFlightTransportsByCities(
        String depart,
        String destination,
        LocalDate dateDepart,
        LocalDate dateRetour
    );

    String getFlightsFromAmadeus(String origin, double maxPrice);
}
