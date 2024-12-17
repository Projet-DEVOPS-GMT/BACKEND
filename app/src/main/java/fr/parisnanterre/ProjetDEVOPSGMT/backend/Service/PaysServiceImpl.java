package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Pays;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.PaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaysServiceImpl implements PaysService {

    private final PaysRepository paysRepository;

    @Autowired
    public PaysServiceImpl(PaysRepository paysRepository) {
        this.paysRepository = paysRepository;
    }

    @Override
    public List<Pays> getAllPays() {
        return paysRepository.findAll();
    }

    @Override
    public Optional<Pays> getPaysById(Long id) {
        return paysRepository.findById(id);
    }

    @Override
    public Pays createPays(Pays pays) {
        return paysRepository.save(pays);
    }

    @Override
    public Pays updatePays(Long id, Pays pays) {
        return paysRepository.findById(id).map(existingPays -> {
            existingPays.setNom(pays.getNom());
            existingPays.setFairScore(pays.getFairScore());
            return paysRepository.save(existingPays);
        }).orElseThrow(() -> new RuntimeException("Pays not found with id: " + id));
    }

    @Override
    public void deletePays(Long id) {
        paysRepository.deleteById(id);
    }
}
