package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

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

    @Override
    public Transport saveTransport(Transport transport) {
        return transportRepository.save(transport);
    }

    @Override
    public void deleteTransport(Long id) {
        transportRepository.deleteById(id);
    }
}
