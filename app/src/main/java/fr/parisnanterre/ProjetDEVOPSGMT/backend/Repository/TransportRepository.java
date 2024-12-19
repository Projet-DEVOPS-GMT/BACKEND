package fr.parisnanterre.ProjetDEVOPSGMT.backend.Repository;

import fr.parisnanterre.ProjetDEVOPSGMT.backend.Model.Transport;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
    List<Transport> findByVilleDepartAndVilleDestination(String villeDepart, String villeDestination);
}
