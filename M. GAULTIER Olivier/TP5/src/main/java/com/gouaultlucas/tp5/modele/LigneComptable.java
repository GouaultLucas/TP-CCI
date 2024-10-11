package com.gouaultlucas.tp5.modele;

import java.io.Serializable;

public class LigneComptable implements Serializable {
    private Integer id;
    private Float somme;
    private String date;
    private String motif;
    private ModePaiement modePaiement;

    public LigneComptable(Float somme, String date, String motif, ModePaiement modePaiement) {
        this.id = null;
        this.somme = somme;
        this.date = date;
        this.motif = motif;
        this.modePaiement = modePaiement;
    }

    public LigneComptable(Integer id, Float somme, String date, String motif, ModePaiement modePaiement) {
        this.id = id;
        this.somme = somme;
        this.date = date;
        this.motif = motif;
        this.modePaiement = modePaiement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getSomme() {
        return somme;
    }

    public void setSomme(Float somme) {
        this.somme = somme;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public ModePaiement getModePaiement() {
        return this.modePaiement;
    }

    public void setModePaiement(ModePaiement modePaiement) {
        this.modePaiement = modePaiement;
    }

    public void afficher() {
        System.out.println(getSomme() + "€ - " + getDate() + " - " + getMotif() + " - " + getModePaiement().toString());
    }
}
