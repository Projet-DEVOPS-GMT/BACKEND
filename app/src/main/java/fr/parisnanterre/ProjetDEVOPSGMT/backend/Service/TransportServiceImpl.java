package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.TransportComparisonDTO;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.TransportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransportServiceImpl implements TransportService {

    private final TransportRepository transportRepository;

    @Autowired
    public TransportServiceImpl(TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    @Override
    public List<TransportComparisonDTO> compareTransports(double distance) {
        List<Transport> transports = transportRepository.findAll();
        List<TransportComparisonDTO> comparisons = new ArrayList<>();

        for (Transport transport : transports) {
            TransportComparisonDTO dto = new TransportComparisonDTO();
            dto.setMode(transport.getType());
            dto.setCost(transport.getPrix().multiply(BigDecimal.valueOf(distance)));
            dto.setEmissions(transport.getTauxCO2().multiply(BigDecimal.valueOf(distance)));
            dto.setTime(calculateEstimatedTime(transport, distance));
            comparisons.add(dto);
        }

        return comparisons;
    }

    private String calculateEstimatedTime(Transport transport, double distance) {
        if (transport.getVitesseMoyenne() <= 0) {
            throw new IllegalArgumentException("La vitesse moyenne doit être supérieure à zéro.");
        }

        double timeInHours = distance / transport.getVitesseMoyenne();
        int hours = (int) timeInHours;
        int minutes = (int) ((timeInHours - hours) * 60);

        return String.format("%dh %02dmin", hours, minutes);
    }
}
