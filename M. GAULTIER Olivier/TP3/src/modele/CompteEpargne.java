package modele;

public class CompteEpargne extends Compte {
    private Float tauxPlacement;

    public CompteEpargne(Integer numero, Float solde, Float tauxPlacement, LigneComptable ligneComptable) {
        super(TypeCompte.EPARGNE, numero, solde, ligneComptable);
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
        System.out.println("Solde: " + getsolde());
        System.out.println("Taux de placement: " + getTauxPlacement());
        System.out.println("Dernière ligne comptable:");
        if(this.ligneComptable != null) this.ligneComptable.afficher();
        else System.out.println("Aucune ligne comptable");
    }

    public static boolean controleTaux(Float tauxPlacement) {
        return 0 <= tauxPlacement;
    }
}
