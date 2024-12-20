package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
   List<Transport> findByVilleDepartAndVilleDestinationAndDateDepartAndDateRetour(
        String villeDepart, 
        String villeDestination, 
        LocalDate dateDepart, 
        LocalDate dateRetour
    );
}
