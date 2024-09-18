package modele;

public class Compte {
    private TypeCompte type;
    private Integer numero;
    private Float soldeBase;
    private Float tauxPlacement;
    private LigneComptable ligneComptable;

    public Compte(TypeCompte type, Integer numero, Float soldeBase, Float tauxPlacement, LigneComptable ligneComptable) {
        this.type = type;
        this.numero = numero;
        this.soldeBase = soldeBase;
        this.tauxPlacement = tauxPlacement;
        this.ligneComptable = ligneComptable;
    }

    public Compte() {}

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

    public Float getSoldeBase() {
        return this.soldeBase;
    }

    public void setSoldeBase(Float soldeBase) {
        this.soldeBase = soldeBase;
    }

    public Float getTauxPlacement() {
        return this.tauxPlacement;
    }

    public void setTauxPlacement(Float tauxPlacement) {
        this.tauxPlacement = tauxPlacement;
    }

    public LigneComptable getLigneComptable() {
        return this.ligneComptable;
    }

    public void setLigneComptable(LigneComptable ligneComptable) {
        this.ligneComptable = ligneComptable;
    }

    public void afficher() {
        System.out.println("Type: " + getType().toString());
        System.out.println("Numéro: " + getNumero());
        System.out.println("Solde de base: " + getSoldeBase());
        System.out.println("Taux de placement: " + getTauxPlacement());
    }
}