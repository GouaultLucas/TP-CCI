package modele;

public class Compte {
    private TypeCompte type;
    private Integer numero;
    private Float solde;
    protected LigneComptable[] lignesComptables;

    public Compte(TypeCompte type, Integer numero, Float solde) {
        this.type = type;
        this.numero = numero;
        this.solde = solde;
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

    public Float getsolde() {
        return this.solde;
    }

    public void setsolde(Float solde) {
        this.solde = solde;
    }

    public LigneComptable[] getLigneComptable() {
        return this.lignesComptables;
    }

    public void afficher() {
        System.out.println("Type: " + getType().toString());
        System.out.println("Numéro: " + getNumero());
        System.out.println("Solde: " + getsolde());
        System.out.println("10 dernières lignes comptables:");

        if(this.lignesComptables != null && this.lignesComptables.length != 0) {
            int n = 0;
            for (LigneComptable ligneComptable : lignesComptables) {
                System.out.print("Ligne n°"+ (n+1) + ": ");
                ligneComptable.afficher();
                n++;
            }
        }
        else System.out.println("Aucune ligne comptable\n");
    }

    private void ajouterLigne(LigneComptable ligne) {
        LigneComptable[] tab;
        if(lignesComptables == null) tab = new LigneComptable[1];
        else if(lignesComptables.length != 10) tab = new LigneComptable[lignesComptables.length + 1];
        else tab = new LigneComptable[10];

        for(int i = 0; i < tab.length - 1; i++) {
            tab[i+1] = this.lignesComptables[i];
        }
        tab[0] = ligne;

        this.lignesComptables = tab;
    }

    public void creerLigneComptable(Float somme, String date, String motif, ModePaiement modePaiement) {
        LigneComptable ligneComptable = new LigneComptable(somme, date, motif, modePaiement);
        ajouterLigne(ligneComptable);
        solde += somme;
    }
}