package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import java.util.List;
import java.util.Optional;

public interface PaysService {
    List<Pays> getAllPays();
    Optional<Pays> getPaysById(Long id);
    Pays createPays(Pays pays);
    Pays updatePays(Long id, Pays pays);
    void deletePays(Long id);
}
