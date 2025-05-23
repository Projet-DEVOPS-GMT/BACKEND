package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Hebergement;
import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HebergementRepository extends JpaRepository<Hebergement, Long> {
    List<Hebergement> findByVille(Ville ville);
    List<Hebergement> findByTypeHebergement(String typeHebergement);
    List<Hebergement> findByVilleAndTypeHebergement(Ville ville, String typeHebergement);
    @Query("SELECT DISTINCT h.typeHebergement FROM Hebergement h")
     List<String> findDistinctTypeHebergement();
}
