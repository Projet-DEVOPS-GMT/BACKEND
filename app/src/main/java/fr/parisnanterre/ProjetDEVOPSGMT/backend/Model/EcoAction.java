package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "eco_action")
public class EcoAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(name="co2saved", nullable = false)
    private Double co2Saved;  // CO2 économisé en kg

    @Column(nullable = false)
    private Date dateAction;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;  // L'utilisateur qui a effectué l'action

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCo2Saved() {
        return co2Saved;
    }

    public void setCo2Saved(Double co2Saved) {
        this.co2Saved = co2Saved;
    }

    public Date getDateAction() {
        return dateAction;
    }

    public void setDateAction(Date dateAction) {
        this.dateAction = dateAction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
