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
@Table(name="consommation")
public class Consommation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal prix;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal tauxCO2;

    @ManyToOne
    @JoinColumn(name = "id_transport")
    private Transport transport;

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

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Hebergement getHebergement() {
        return hebergement;
    }

    public void setHebergement(Hebergement hebergement) {
        this.hebergement = hebergement;
    }

    public Restauration getRestauration() {
        return restauration;
    }

    public void setRestauration(Restauration restauration) {
        this.restauration = restauration;
    }

    public Voyage getVoyage() {
        return voyage;
    }

    public void setVoyage(Voyage voyage) {
        this.voyage = voyage;
    }

    @ManyToOne
    @JoinColumn(name = "id_hebergement")
    private Hebergement hebergement;

    @ManyToOne
    @JoinColumn(name = "id_restauration")
    private Restauration restauration;

    @ManyToOne
    @JoinColumn(name = "id_voyage", nullable = false)
    private Voyage voyage;

}

