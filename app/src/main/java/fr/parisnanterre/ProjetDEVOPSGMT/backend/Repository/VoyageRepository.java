package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Voyage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findByUserId(Long userId);
}
