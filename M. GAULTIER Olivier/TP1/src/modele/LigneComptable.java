package modele;

public class LigneComptable {
    private Compte compte;
    private Float somme;
    private String date;
    private String motif;
    private ModePaiement modePaiement;

    LigneComptable(Compte compte, Float somme, String date, String motif, ModePaiement modePaiement) {
        this.compte = compte;
        this.somme = somme;
        this.date = date;
        this.motif = motif;
        this.modePaiement = modePaiement;
    }

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
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
        System.out.println("Numéro Compte: " + getCompte().getNumero());
        System.out.println("Somme: " + getSomme());
        System.out.println("Date: " + getDate());
        System.out.println("Motif: " + getMotif());
        System.out.println("Mode de paiement: " + getModePaiement().toString());
    }
}
