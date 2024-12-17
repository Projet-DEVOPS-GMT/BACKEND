package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Restauration;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.RestaurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurationServiceImpl implements RestaurationService {

    private final RestaurationRepository restaurationRepository;

    @Autowired
    public RestaurationServiceImpl(RestaurationRepository restaurationRepository) {
        this.restaurationRepository = restaurationRepository;
    }

    @Override
    public List<Restauration> getAllRestaurations() {
        return restaurationRepository.findAll();
    }

    @Override
    public Optional<Restauration> getRestaurationById(Long id) {
        return restaurationRepository.findById(id);
    }

    @Override
    public Restauration createRestauration(Restauration restauration) {
        return restaurationRepository.save(restauration);
    }

    @Override
    public Restauration updateRestauration(Long id, Restauration restauration) {
        return restaurationRepository.findById(id).map(existingRestauration -> {
            existingRestauration.setTypeRestaurant(restauration.getTypeRestaurant());
            existingRestauration.setPrixMoyenne(restauration.getPrixMoyenne());
            existingRestauration.setTauxCO2(restauration.getTauxCO2());
            return restaurationRepository.save(existingRestauration);
        }).orElseThrow(() -> new RuntimeException("Restauration not found with id: " + id));
    }

    @Override
    public void deleteRestauration(Long id) {
        restaurationRepository.deleteById(id);
    }
}
