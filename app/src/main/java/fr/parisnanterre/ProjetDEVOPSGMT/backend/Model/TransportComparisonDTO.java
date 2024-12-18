package fr.parisnanterre.ProjetDEVOPSGMT.backend.Model;

import java.math.BigDecimal;

public class TransportComparisonDTO {
    private String mode;
    private BigDecimal cost;
    private BigDecimal emissions;
    private String time;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getEmissions() {
        return emissions;
    }

    public void setEmissions(BigDecimal emissions) {
        this.emissions = emissions;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void calculateEstimatedTime(double distance, double vitesseMoyenne) {
        if (vitesseMoyenne <= 0) {
            throw new IllegalArgumentException("La vitesse moyenne doit être supérieure à zéro.");
        }

        double timeInHours = distance / vitesseMoyenne;
        int hours = (int) timeInHours;
        int minutes = (int) ((timeInHours - hours) * 60);

        this.time = String.format("%dh %02dmin", hours, minutes);
    }
}

