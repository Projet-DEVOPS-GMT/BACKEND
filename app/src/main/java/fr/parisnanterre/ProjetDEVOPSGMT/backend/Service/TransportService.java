package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;

import java.util.List;
import java.util.Optional;

public interface TransportService {
    List<Transport> getAllTransports();
    Optional<Transport> getTransportById(Long id);
    Transport saveTransport(Transport transport);
    void deleteTransport(Long id);
}
