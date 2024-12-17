package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="restauration")
public class Restauration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String typeRestaurant;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prixMoyenne;

    @Column(nullable = true, precision = 10, scale = 2)
    private BigDecimal tauxCO2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeRestaurant() {
        return typeRestaurant;
    }

    public void setTypeRestaurant(String typeRestaurant) {
        this.typeRestaurant = typeRestaurant;
    }

    public BigDecimal getPrixMoyenne() {
        return prixMoyenne;
    }

    public void setPrixMoyenne(BigDecimal prixMoyenne) {
        this.prixMoyenne = prixMoyenne;
    }

    public BigDecimal getTauxCO2() {
        return tauxCO2;
    }

    public void setTauxCO2(BigDecimal tauxCO2) {
        this.tauxCO2 = tauxCO2;
    }

}
