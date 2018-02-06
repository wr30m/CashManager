package com.example.kirazavrik.cashmanager.models;

/**
 * Created by kirazavrik on 18.1.18.
 */

public class Category {

    private String label;
    private double totalWaste;

    public Category(String label) {
        this.label = label;
        totalWaste = 0.00;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getTotalWaste() {
        return totalWaste;
    }

    public void setTotalWaste(double totalWaste) {
        this.totalWaste = totalWaste;
    }

}
