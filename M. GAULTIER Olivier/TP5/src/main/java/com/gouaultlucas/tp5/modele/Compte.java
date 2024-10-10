package com.gouaultlucas.tp5.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Compte implements Serializable, Comparable<Compte> {
    private TypeCompte type;
    private Integer numero;
    private Float solde;
    protected ArrayList<LigneComptable> lignesComptables;

    public Compte(TypeCompte type, Integer numero, Float solde) {
        this.type = type;
        this.numero = numero;
        this.solde = solde;
        this.lignesComptables = new ArrayList<LigneComptable>();
    }

    public TypeCompte getType() {
        return this.type;
    }


    public void setType(TypeCompte type) {
        this.type = type;
    }

    public Integer getNumero() {
        return this.numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Float getSolde() {
        return this.solde;
    }

    public void setsolde(Float solde) {
        this.solde = solde;
    }

    public ArrayList<LigneComptable> getLigneComptable() {
        return this.lignesComptables;
    }

    public void afficher() {
        System.out.println("Type: " + getType().toString());
        System.out.println("Numéro: " + getNumero());
        System.out.println("Solde: " + getSolde() + "€");
        System.out.println("10 dernières lignes comptables:");

        if(this.lignesComptables.size() != 0) {
            int n = 0;
            for (LigneComptable ligneComptable : lignesComptables) {
                System.out.print("Ligne n°"+ (n+1) + ": ");
                ligneComptable.afficher();
                n++;
            }
        }
        else System.out.println("Aucune ligne comptable\n");
    }

    public void afficherUneLigne() {
        System.out.println(
            "N°" + getNumero() + "; "
            + "Solde: " + getSolde() + "€; "
            + "Lignes comptables: " + getLigneComptable().size() + "; "
            + "Type: " + getType() + "; "
        );
    }

    private void ajouterLigne(LigneComptable ligne) {
        this.lignesComptables.add(0, ligne);
    }

    public void creerLigneComptable(Float somme, String date, String motif, ModePaiement modePaiement) {
        LigneComptable ligneComptable = new LigneComptable(somme, date, motif, modePaiement);
        ajouterLigne(ligneComptable);
        solde += somme;
    }

    @Override
    public int compareTo(Compte compte) {
        return this.numero.compareTo(compte.getNumero());
    }
}