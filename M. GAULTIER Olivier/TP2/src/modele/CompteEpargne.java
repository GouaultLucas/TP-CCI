package modele;

public class CompteEpargne extends Compte {
    private Float tauxPlacement;

    public CompteEpargne(TypeCompte type, Integer numero, Float solde, Float tauxPlacement, LigneComptable ligneComptable) {
        super(type, numero, solde, ligneComptable);
        this.tauxPlacement = tauxPlacement;
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
        System.out.println("Solde: " + getsolde());
        System.out.println("Taux de placement: " + getTauxPlacement());
        System.out.println("Dernière ligne comptable:");
        if(this.ligneComptable != null) this.ligneComptable.afficher();
        else System.out.println("Aucune ligne comptable");
    }
}
