package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import java.math.BigDecimal;

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

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tauxCO2;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal estimationPrix;

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

    public BigDecimal getTauxCO2() {
        return tauxCO2;
    }

    public void setTauxCO2(BigDecimal tauxCO2) {
        this.tauxCO2 = tauxCO2;
    }

    public BigDecimal getEstimationPrix() {
        return estimationPrix;
    }

    public void setEstimationPrix(BigDecimal estimationPrix) {
        this.estimationPrix = estimationPrix;
    }

}