

import java.util.Date;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import modele.Compte;
import modele.CompteEpargne;
import modele.ModePaiement;
import modele.TypeCompte;

public class App {
    private static final String fichierComptes = "comptes.ser";
    private static ArrayList<Compte> comptes;

    private static void sauvegarderDonnees() {
        ObjectOutputStream oos = null;

        try {
            final FileOutputStream fichier = new FileOutputStream(fichierComptes);
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(comptes);
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void chargerDonnees() {
        if(new File(fichierComptes).exists()) {
            ObjectInputStream ois = null;

            try {
                final FileInputStream fichier = new FileInputStream(fichierComptes);
                ois = new ObjectInputStream(fichier);
                comptes = (ArrayList<Compte>) ois.readObject();
            } catch (final java.io.IOException e) {
                e.printStackTrace();
            } catch (final ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (final IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else comptes = new ArrayList<Compte>();
    }

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

        chargerDonnees();

        while (!quitter) {
            String choix = menuPrincipal();

            switch (choix) {
                case "1":
                    menuCreationCompte();
                    sauvegarderDonnees();
                    break;

                case "2":
                    menuAfficherCompte();
                    break;

                case "3":
                    menuCreerLigneComptable();
                    sauvegarderDonnees();
                    break;

                case "4":
                    afficherComptes();
                    break;
                case "5":
                    quitter = true;
                    break;

                default:
                    break;
            }
        }
    }

    public static String menuPrincipal() {
        System.out.println("Que voulez vous faire ?\n");
        System.out.println("\t1. Créer un compte");
        System.out.println("\t2. Afficher un compte");
        System.out.println("\t3. Créer une ligne comptable");
        System.out.println("\t4. Afficher tous les comptes");
        System.out.println("\t5. Quitter\n\n");

        String choix = attendreSaisieClavier();

        return choix;
    }

    private static Scanner scanner = new Scanner(System.in);

    public static String attendreSaisieClavier() {
        return scanner.nextLine().toString();
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
                    tauxPlacement = Float.parseFloat(saisie);

                    if(CompteEpargne.controleTaux(tauxPlacement)) suivant = true;
                    else System.out.println("Le taux de placement ne peut pas être négatif.");
                } catch(NumberFormatException err) {
                    System.out.println("Vous n'avez pas entré de taux de placement valide (uniquement composé de chiffres)");
                }
            }
        }

        if(type.equals(TypeCompte.EPARGNE)) {
            CompteEpargne compte = new CompteEpargne(numero, soldeBase, tauxPlacement);
            comptes.add(compte);

            System.out.println("\nLe compte avec ces informations a été créé:");
            compte.afficher();

        } else {
            Compte compte = new Compte(type, numero, soldeBase);
            comptes.add(compte);

            System.out.println("\nLe compte avec ces informations a été créé:");
            compte.afficher();
        }
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

        if(!compteExiste(numero)) System.out.println("Ce compte n'existe pas");
        else getCompte(numero).afficher();

        System.out.println();
    }

    public static void menuCreerLigneComptable() {
        boolean suivant = false;

        Integer numero = 0;
        Float somme = 0F;
        Date date = null;
        String motif = "";
        ModePaiement modePaiement = null;

        while(!suivant) {
            System.out.println("\nEntrez le numéro du compte: ");
            String saisie = attendreSaisieClavier();

            try {
                numero = Integer.parseInt(saisie);

                if(!compteExiste(numero)) System.out.println("Ce compte n'existe pas");
                else suivant = true;
            } catch(NumberFormatException err) {
                System.out.println("Vous n'avez pas entré de code valide (uniquement composé de chiffres)");
            }
        }

        suivant = false;

        while(!suivant) {
            System.out.println("\nEntrez la somme de la ligne de comptable: ");
            String saisie = attendreSaisieClavier();

            try {
                somme = Float.parseFloat(saisie);
                suivant = true;
            } catch(NumberFormatException err) {
                System.out.println("Vous n'avez pas entré de somme valide (uniquement composé de chiffres)");
            }
        }

        suivant = false;

        while(!suivant) {
            System.out.println("\nEntrez la date de la ligne de comptable: ");
            String saisie = attendreSaisieClavier();

            try {
                date = new SimpleDateFormat("dd/MM/yyyy").parse(saisie);
                suivant = true;
            } catch(ParseException err) {
                System.out.println("Vous n'avez pas entré une date valide");
            }
        }

        suivant = false;

        while(!suivant) {
            System.out.println("\nEntrez le motif de la ligne de comptable: ");
            motif = attendreSaisieClavier();
            suivant = true;
        }

        suivant = false;

        while(!suivant) {
            System.out.println("\nEntrez le mode de paiement de la ligne de comptable: ");
            System.out.println("1. CB");
            System.out.println("2. Cheque");
            System.out.println("3. Virement\n");
            String saisie = attendreSaisieClavier();

            try {
                switch(saisie) {
                    case "1":
                        modePaiement = ModePaiement.CB;
                        suivant = true;
                        break;
                    case "2":
                        modePaiement = ModePaiement.CHEQUE;
                        suivant = true;
                        break;
                    case "3":
                        modePaiement = ModePaiement.VIREMENT;
                        suivant = true;
                        break;
                    default:
                        System.out.println("Vous n'avez pas entré un mode de paiement valide");
                        suivant = false;
                }
            } catch(IllegalArgumentException err) {
                System.out.println("Vous n'avez pas entré un mode de paiement valide");
            }
        }
        
        Compte compte = getCompte(numero);

        String dateString = new SimpleDateFormat("dd/MM/yyyy").format(date);

        compte.creerLigneComptable(somme, dateString, motif, modePaiement);
    }

    private static void triBulles(ArrayList<Compte> liste) {
        int n = liste.size();
        boolean echange;

        for (int i = 0; i < n - 1; i++) {
            echange = false;
            for (int j = 0; j < n - 1 - i; j++) {
                // Comparer le numéro des comptes
                if (liste.get(j).getNumero() > liste.get(j + 1).getNumero()) {
                    // Échanger les éléments
                    Compte temp = liste.get(j);
                    liste.set(j, liste.get(j + 1));
                    liste.set(j + 1, temp);
                    echange = true;
                }
            }
            // Si aucun échange n'a eu lieu, le tableau est déjà trié
            if (!echange) {
                break;
            }
        }
    }

    public static void afficherComptes() {
        System.out.println("\nAffichage de tous les comptes:");

        if(comptes.size() == 0) {
            System.out.println("Aucun compte");
        } else {
            Collections.sort(comptes);

            for (Compte compte : comptes) {
                if(compte.getType().equals(TypeCompte.EPARGNE)) ((CompteEpargne) compte).afficherUneLigne();
                else compte.afficherUneLigne();
            }
        }

        System.out.println();
    }
}
