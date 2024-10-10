package modele;

public class Compte {
    private TypeCompte type;
    private Integer numero;
    private Float solde;
    protected LigneComptable ligneComptable;

    public Compte(TypeCompte type, Integer numero, Float solde, LigneComptable ligneComptable) {
        this.type = type;
        this.numero = numero;
        this.solde = solde;
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

    public Float getsolde() {
        return this.solde;
    }

    public void setsolde(Float solde) {
        this.solde = solde;
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
        System.out.println("Solde: " + getsolde());
        System.out.println("Dernière ligne comptable:");
        if(this.ligneComptable != null) this.ligneComptable.afficher();
        else System.out.println("Aucune ligne comptable");
    }

    public void creerLigneComptable(Float somme, String date, String motif, ModePaiement modePaiement) {
        this.ligneComptable = new LigneComptable(this, somme, date, motif, modePaiement);
        solde += somme;
    }
}