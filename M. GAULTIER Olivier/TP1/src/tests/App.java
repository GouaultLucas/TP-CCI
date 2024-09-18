package tests;

import java.util.ArrayList;
import java.util.Scanner;

import modele.Compte;
import modele.TypeCompte;

public class App {
    private static ArrayList<Compte> comptes = new ArrayList<Compte>();
    private static boolean compteExiste(Integer numero) {
        boolean retour = false;

        for (Compte compte : comptes) {
            if(compte.getNumero().equals(numero)) {
                retour = true;
                break;
            }
        }

        return retour;
    }
    private static Compte getCompte(Integer numero) {
        Compte retour = null;

        for (Compte compte : comptes) {
            if(compte.getNumero().equals(numero)) retour = compte;
        }

        return retour;
    }
    public static void main(String[] args) {
        boolean quitter = false;

        while (!quitter) {
            String choix = MenuPrincipal();

            switch (choix) {
                case "1":
                    menuCreationCompte();
                    break;

                case "2":
                    menuAfficherCompte();
                    break;

                case "3":
                    
                    break;

                case "4":
                    quitter = true;
                    break;

                default:
                    break;
            }
        }
    }

    public static String MenuPrincipal() {
        System.out.println("Que voulez vous faire ?\n");
        System.out.println("\t1. Créer un compte");
        System.out.println("\t2. Afficher un compte");
        System.out.println("\t3. Créer une ligne comptable");
        System.out.println("\t4. Quitter\n\n");

        String choix = attendreSaisieClavier();

        return choix;
    }

    public static String attendreSaisieClavier() {
        String retour = null;

        Scanner scanner = new Scanner(System.in);

        retour = scanner.nextLine();

        scanner.close();

        return retour;
    }

    public static void menuCreationCompte() {
        System.out.println("\nVous avez choisi de créer un nouveau compte.");

        TypeCompte type = TypeCompte.COURANT;
        Integer numero = 0;
        Float soldeBase = 0F, tauxPlacement = 0F;

        boolean suivant = false;
        String saisie;

        // TYPE
        while(!suivant) {
            System.out.println("\nQuel est le type de ce nouveau compte ?");
            System.out.println("\t1. COURANT");
            System.out.println("\t2. JOINT");
            System.out.println("\t3. EPARGNE\n\n");

            saisie = attendreSaisieClavier();

            suivant = true;

                 if(saisie.equals("1")) type = TypeCompte.COURANT;
            else if(saisie.equals("2")) type = TypeCompte.JOINT;
            else if(saisie.equals("3")) type = TypeCompte.EPARGNE;
            else {
                System.out.println("Le type entré est invalide.");
                suivant = false;
            }
        }

        suivant = false;

        // NUMERO
        while(!suivant) {
            System.out.println("\nQuel est le numéro de ce nouveau compte ?");
            saisie = attendreSaisieClavier();

            try {
                numero = Integer.parseInt(saisie);
                
                if(compteExiste(numero)) System.out.println("Ce numéro de compte existe déjà");
                else suivant = true;
            } catch(NumberFormatException err) {
                System.out.println("Vous n'avez pas entré de code valide (uniquement composé de chiffres)");
            }
        }

        suivant = false;

        // SOLDE
        while(!suivant) {
            System.out.println("\nQuel est le solde de ce nouveau compte ?");
            saisie = attendreSaisieClavier();

            try {
                soldeBase = Float.parseFloat(saisie);
                suivant = true;
            } catch(NumberFormatException err) {
                System.out.println("Vous n'avez pas entré de solde valide (uniquement composé de chiffres)");
            }
        }

        suivant = false;
        
        // TAUX PLACEMENT
        if(type.equals(TypeCompte.EPARGNE)) {
            while(!suivant) {
                System.out.println("\nQuel est le taux de placement de ce nouveau compte ?");
                saisie = attendreSaisieClavier();
    
                try {
                    soldeBase = Float.parseFloat(saisie);
                    suivant = true;
                } catch(NumberFormatException err) {
                    System.out.println("Vous n'avez pas entré de taux de placement valide (uniquement composé de chiffres)");
                }
            }
        }

        Compte compte = new Compte(type, numero, soldeBase, tauxPlacement, null);
        comptes.add(compte);

        System.out.println("\nLe compte avec ces informations a été créé:");
        compte.afficher();
    }

    public static void menuAfficherCompte() {
        boolean suivant = false;

        Integer numero = 0;

        while(!suivant) {
            System.out.println("\nEntrez le numéro du compte à afficher: ");

            String saisie = attendreSaisieClavier();

            try {
                numero = Integer.parseInt(saisie);
                suivant = true;
            } catch(NumberFormatException err) {
                System.out.println("Vous n'avez pas entré de code valide (uniquement composé de chiffres)");
            }
        }

        Compte compte = getCompte(numero);

        if(compte == null) System.out.println("Ce compte n'existe pas");
        else compte.afficher();

        System.out.println();
    }
}
