package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import java.util.List;
import java.util.Optional;

public interface RestaurationService {
    List<Restauration> getAllRestaurations();
    Optional<Restauration> getRestaurationById(Long id);
    Restauration createRestauration(Restauration restauration);
    Restauration updateRestauration(Long id, Restauration restauration);
    void deleteRestauration(Long id);
}
