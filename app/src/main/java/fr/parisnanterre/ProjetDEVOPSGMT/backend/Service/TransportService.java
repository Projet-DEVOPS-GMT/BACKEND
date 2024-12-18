package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.TransportComparisonDTO;

import java.util.List;

public interface TransportService {
    List<TransportComparisonDTO> compareTransports(double distance);
}
