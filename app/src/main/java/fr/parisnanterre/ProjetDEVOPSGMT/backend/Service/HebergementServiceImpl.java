package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.HebergementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HebergementServiceImpl implements HebergementService {

    private final HebergementRepository hebergementRepository;

    @Autowired
    public HebergementServiceImpl(HebergementRepository hebergementRepository) {
        this.hebergementRepository = hebergementRepository;
    }

    @Override
    public List<Hebergement> getAllHebergements() {
        return hebergementRepository.findAll();
    }

    @Override
    public Optional<Hebergement> getHebergementById(Long id) {
        return hebergementRepository.findById(id);
    }

    @Override
    public Hebergement saveHebergement(Hebergement hebergement) {
        return hebergementRepository.save(hebergement);
    }

    @Override
    public void deleteHebergement(Long id) {
        hebergementRepository.deleteById(id);
    }
}
