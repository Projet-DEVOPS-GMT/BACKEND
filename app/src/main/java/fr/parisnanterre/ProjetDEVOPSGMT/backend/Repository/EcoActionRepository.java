package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.EcoAction;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EcoActionRepository extends JpaRepository<EcoAction, Long> {

    List<EcoAction> findByUser(User user);
}
