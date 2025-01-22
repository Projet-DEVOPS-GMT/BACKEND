package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "hebergement")
public class Hebergement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String typeHebergement;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal tauxCO2;

    private String description;

    private String equipement;

    private String photo;

    private String conditions_reservation;

    // Relation avec la table Ville
    @ManyToOne
    @JoinColumn(name = "id_ville", nullable = false) // Lié à la table ville
    private Ville ville;

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeHebergement() {
        return typeHebergement;
    }

    public void setTypeHebergement(String typeHebergement) {
        this.typeHebergement = typeHebergement;
    }

    public BigDecimal getPrix() {
        return prix;
    }

    public void setPrix(BigDecimal prix) {
        this.prix = prix;
    }

    public BigDecimal getTauxCO2() {
        return tauxCO2;
    }

    public void setTauxCO2(BigDecimal tauxCO2) {
        this.tauxCO2 = tauxCO2;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getConditionsReservation() {
        return conditions_reservation;
    }

    public void setConditionsReservation(String conditions_reservation) {
        this.conditions_reservation = conditions_reservation;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}