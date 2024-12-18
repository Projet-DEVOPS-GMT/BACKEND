package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.VoyageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VoyageServiceImpl implements VoyageService {

    private final VoyageRepository voyageRepository;

    @Autowired
    public VoyageServiceImpl(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    @Override
    public List<Voyage> getAllVoyages() {
        return voyageRepository.findAll();
    }

    @Override
    public Optional<Voyage> getVoyageById(Long id) {
        return voyageRepository.findById(id);
    }

    @Override
    public Voyage saveVoyage(Voyage voyage) {
        return voyageRepository.save(voyage);
    }

    @Override
    public void deleteVoyage(Long id) {
        voyageRepository.deleteById(id);
    }
}
