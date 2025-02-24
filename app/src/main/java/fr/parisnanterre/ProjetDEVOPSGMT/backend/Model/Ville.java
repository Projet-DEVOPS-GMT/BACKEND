package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "id_pays", nullable = false)
    private Pays pays;

    @Column(nullable = false)
    private Long population;

    @Column(nullable = false)
    private Double pib; // Produit Intérieur Brut en millions

    @Column(nullable = false)
    private Double tauxCo2; // Taux de CO₂ en tonnes/habitant/an



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getPib() {
        return pib;
    }

    public void setPib(Double pib) {
        this.pib = pib;
    }

    public Double getTauxCo2() {
        return tauxCo2;
    }

    public void setTauxCo2(Double tauxCo2) {
        this.tauxCo2 = tauxCo2;
    }

}