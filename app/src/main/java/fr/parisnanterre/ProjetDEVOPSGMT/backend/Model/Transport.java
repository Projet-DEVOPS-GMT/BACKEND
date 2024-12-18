package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transport")
public class Transport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tauxCO2;

    @Column(nullable = false)
    private double vitesseMoyenne;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public double getVitesseMoyenne() {
        return vitesseMoyenne;
    }

    public void setVitesseMoyenne(double vitesseMoyenne) {
        this.vitesseMoyenne = vitesseMoyenne;
    }
}
