package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;
    private final DestinationsService destinationsService;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository, DestinationsService destinationsService) {
        this.transportRepository = transportRepository;
        this.destinationsService = destinationsService;
    }

    @Override
    public List<Transport> getAllTransports() {
        return transportRepository.findAll();
    }

    @Override
    public Optional<Transport> getTransportById(Long id) {
        return transportRepository.findById(id);
    }

    @Override
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }

    @Override
    public List<Transport> getNonFlightTransportsByCities(String depart, String destination, LocalDate dateDepart, LocalDate dateRetour) {
        return transportRepository.findByVilleDepartAndVilleDestinationAndDateDepartAndDateRetourAndTypeTransportNot(
            depart, destination, dateDepart, dateRetour, "Avion"
        );
    }

    @Override
    public String getFlightsFromAmadeus(String origin, double maxPrice) {
        return destinationsService.getDestinations(origin, maxPrice);
    }
}
