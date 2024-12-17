package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="hebergement")
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

    private String condition;

}