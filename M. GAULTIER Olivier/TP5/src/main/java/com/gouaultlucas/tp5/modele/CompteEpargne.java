package com.gouaultlucas.tp5.modele;

public class CompteEpargne extends Compte {
    private Float tauxPlacement;

    public CompteEpargne(Integer numero, Float solde, Float tauxPlacement) {
        super(TypeCompte.EPARGNE, numero, solde);
        this.tauxPlacement = controleTaux(tauxPlacement) ? tauxPlacement : null;
    }

    public Float getTauxPlacement() {
        return this.tauxPlacement;
    }

    public void setTauxPlacement(Float tauxPlacement) {
        this.tauxPlacement = tauxPlacement;
    }

    public void afficher() {
        System.out.println("Type: " + getType().toString());
        System.out.println("Numéro: " + getNumero());
        System.out.println("Solde: " + getSolde());
        System.out.println("Taux de placement: " + getTauxPlacement());
        System.out.println("Dernière ligne comptable:");
        
        if(this.lignesComptables.size() != 0) {
            int n = 0;
            for (LigneComptable ligneComptable : lignesComptables) {
                n++;
                System.out.print("Ligne n°"+ (n) + ": ");
                ligneComptable.afficher();
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
            + "Taux Placement: " + getTauxPlacement() + "%; "
        );
    }

    public static boolean controleTaux(Float tauxPlacement) {
        return 0 <= tauxPlacement;
    }
}
