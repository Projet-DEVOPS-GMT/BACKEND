package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.EcoAction;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository.EcoActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EcoActionServiceImpl implements EcoActionService {

    private final EcoActionRepository ecoActionRepository;

    @Autowired
    public EcoActionServiceImpl(EcoActionRepository ecoActionRepository) {
        this.ecoActionRepository = ecoActionRepository;
    }

    @Override
    public EcoAction saveAction(EcoAction ecoAction) {
        // Associer l'utilisateur avec l'action avant de la sauvegarder
        return ecoActionRepository.save(ecoAction);
    }

    @Override
    public List<EcoAction> getActionsByUser(User user) {
        // Récupérer toutes les actions associées à l'utilisateur
        return ecoActionRepository.findByUser(user);
    }

    @Override
    public double getTotalCO2Saved(User user) {
        // Calculer la somme du CO2 économisé par l'utilisateur
        return ecoActionRepository.findByUser(user)
                .stream()
                .mapToDouble(EcoAction::getCo2Saved)
                .sum();
    }
}
