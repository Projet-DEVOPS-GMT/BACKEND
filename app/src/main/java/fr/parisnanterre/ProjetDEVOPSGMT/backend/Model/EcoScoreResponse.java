package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;
public class EcoScoreResponse {
    private String city;
    private double score;
    private Ville details;

 
    public EcoScoreResponse(String nom, double ecoScore, Ville ville) {
        this.details = ville;
        this.city =nom;
        this.score = ecoScore;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    
    public double getScore() {
        return score;
    }

    
    public void setScore(double score) {
        this.score = score;
    }

   
    public Ville getDetails() {
        return details;
    }

    public void setDetails(Ville details) {
        this.details = details;
    }

}