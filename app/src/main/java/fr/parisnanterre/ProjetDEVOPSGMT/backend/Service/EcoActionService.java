package fr.parisnanterre.ProjetDEVOPSGMT.backend.Service;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.EcoAction;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;

import java.util.List;

public interface EcoActionService {

    // Enregistrer une action écologique
    EcoAction saveAction(EcoAction ecoAction);

    // Récupérer toutes les actions d'un utilisateur
    List<EcoAction> getActionsByUser(User user);

    // Calculer le total de CO2 économisé par un utilisateur
    double getTotalCO2Saved(User user);
}
