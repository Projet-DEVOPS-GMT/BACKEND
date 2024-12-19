package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="transport")
public class Transport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String typeTransport;

    @Column(nullable = false)
    private double tauxCO2;

    @Column(nullable = false)
    private double estimationPrix;

    @Column(name = "ville_depart", nullable = true)
    private String villeDepart;

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    @Column(name = "ville_destination", nullable = true)
    private String villeDestination;


    public String getVilleDestination() {
        return villeDestination;
    }

    public void setVilleDestination(String villeDestination) {
        this.villeDestination = villeDestination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeTransport() {
        return typeTransport;
    }

    public void setTypeTransport(String typeTransport) {
        this.typeTransport = typeTransport;
    }

    public double getTauxCO2() {
        return tauxCO2;
    }

    public void setTauxCO2(double tauxCO2) {
        this.tauxCO2 = tauxCO2;
    }

    public double getEstimationPrix() {
        return estimationPrix;
    }

    public void setEstimationPrix(double estimationPrix) {
        this.estimationPrix = estimationPrix;
    }

}