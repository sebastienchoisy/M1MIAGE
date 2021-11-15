package Ex1;

import java.util.ArrayList;
import java.util.function.Consumer;


public class ueList {
    ArrayList liste = new ArrayList<ue>();

    public class ue {
        String module;
        Integer anneeDeCreation;
        String diplome;
        String enseignant;
        String controle;
        Boolean rattrapage;
        public ue (String module,Integer anneeDeCreation,String diplome,String enseignant,String controle,Boolean rattrapage) {
            this.module = module;
            this.anneeDeCreation = anneeDeCreation;
            this.diplome = diplome;
            this.enseignant = enseignant;
            this.controle = controle;
            this.rattrapage = rattrapage;
        }

        public String toString() {
            return "Module: " + this.module+" | Année de création: " +this.anneeDeCreation+" | Diplôme: "+this.diplome+" | Enseignant: "+this.enseignant+" | Type de contrôle: "+this.controle+" | Rattrapage: "+this.rattrapage;
        }
    }

    public void remplirListe() {
        this.liste.add(new ue("Compétences transversales",1995,"MIAGE L3","Dupont","QCM",true));
        this.liste.add(new ue("Conception objet et programmation",1998,"MIAGE M1","Lahire","Projet",true));
        this.liste.add(new ue("Découverte du métier",2001,"MIAGE M2","Renevier","Projet",false));
        this.liste.add(new ue("Marketing et environnement de l'entreprise",2004,"MIAGE L3","Tounsi","questions de synthèse",true));
        this.liste.add(new ue("Système et réseaux",1992,"MIAGE M1","Menez","QCM",true));
    }

    public ArrayList getListe() {
        return this.liste;
    }

    public void resetListe() {
        this.liste = new ArrayList<ue>();
        this.remplirListe();
    }

    public String toString() {
        String listeString = "";
        for(int i=0;i<this.liste.size();i++) {
            listeString = listeString + this.liste.get(i).toString() + "\n";
        }
        return listeString;
    }

    /*_________________________________________________________CLASSES INTERNES STATIQUES_______________________________________________________*/

    // STATIC CONSUMER QUI INCREMENT L'ANNEE
    public static class staticIncrementationAnneeConsumer implements Consumer<ue> {

        @Override
        public void accept(ue ue) {
            ue.anneeDeCreation++;
        }
    }
    // STATIC CONSUMER QUI INCREMENT L'ANNEE SI L'ENSEIGNANT S'APPELLE DUPONT
    public static class staticIncrementationAnneeIfDupontConsumer implements Consumer<ue> {

        @Override
        public void accept(ue ue) {
            if (ue.enseignant == "Dupont") {
                ue.anneeDeCreation++;
            }
        }
    }
    // STATIC CONSUMER QUI SUPPRIMER LE RATTRAPAGE SI LE TYPE DE CONTRÔLE ASSOCIE EST UN QCM
    public static class staticRattrapageImpossibleSiQcmConsumer implements Consumer<ue> {

            @Override
        public void accept(ue ue) {
            if(ue.controle == "QCM") {
                ue.rattrapage = false;
            }
        }
    }

    /*_________________________________________________________CLASSES INTERNES NON STATIQUES_______________________________________________________*/

    // STATIC CONSUMER QUI INCREMENT L'ANNEE
    public class nonStaticIncrementationAnneeConsumer implements Consumer<ue> {

        @Override
        public void accept(ue ue) {
            ue.anneeDeCreation++;
        }
    }
    // STATIC CONSUMER QUI INCREMENT L'ANNEE SI L'ENSEIGNANT S'APPELLE DUPONT
    public class nonStaticIncrementationAnneeIfDupont implements Consumer<ue> {

        @Override
        public void accept(ue ue) {
            if (ue.enseignant == "Dupont") {
                ue.anneeDeCreation++;
            }
        }
    }
    // STATIC CONSUMER QUI SUPPRIMER LE RATTRAPAGE SI LE TYPE DE CONTRÔLE ASSOCIE EST UN QCM
    public class nonStaticRattrapageImpossibleSiQcm implements Consumer<ue> {

        @Override
        public void accept(ue ue) {
            if(ue.controle == "QCM"){
                ue.rattrapage = false;
            }
        }
    }

    public void traitementIncrementationAnneeNonStatic(ArrayList liste) {
        liste.forEach(new nonStaticIncrementationAnneeConsumer());
    }

    public void traitementIncrementationAnneeIfDupontNonStatic(ArrayList liste) {
        liste.forEach(new nonStaticIncrementationAnneeIfDupont());
    }
    public void traitementRattrapageImpossibleSiQcmNonStatic(ArrayList liste) {
        liste.forEach(new nonStaticRattrapageImpossibleSiQcm());
    }

    /*_________________________________________________________CLASSES INTERNES NON STATIQUES_______________________________________________________*/

    public void traitementIncrementationAnneeLocal(ArrayList liste) {
        class localIncrementationAnneeConsumer implements Consumer<ue> {

            @Override
            public void accept(ue ue) {
                ue.anneeDeCreation++;
            }
        }
        liste.forEach(new localIncrementationAnneeConsumer());
    }

    public void traitementIncrementationAnneeIfDupontLocal(ArrayList liste) {
        class localIncrementationAnneeIfDupontConsumer implements Consumer<ue> {

            @Override
            public void accept(ue ue) {
                if (ue.enseignant == "Dupont") {
                    ue.anneeDeCreation++;
                }
            }
        }
        liste.forEach(new localIncrementationAnneeIfDupontConsumer());
    }

    public void traitementRattrapageImpossibleSiQcmLocal(ArrayList liste) {
        class localRattrapageImpossibleSiQcmConsumer implements Consumer<ue> {

            @Override
            public void accept(ue ue) {
                if(ue.controle == "QCM") {
                    ue.rattrapage = false;
                }
            }
        }
        liste.forEach(new localRattrapageImpossibleSiQcmConsumer());
    }


    public static void main(String[] args) {
        ueList liste = new ueList();
        liste.remplirListe();

        System.out.println(" ----------------------------- TRAITEMENT AVEC CLASSES INTERNES STATIQUES ----------------------------- \n");
        System.out.println("LISTE INITIALE SANS TRAITEMENT");
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE avec une classe interne statique \n");
        liste.getListe().forEach(new staticIncrementationAnneeConsumer());
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE si l'enseignant est Dupont avec une classe interne statique \n");
        liste.getListe().forEach(new staticIncrementationAnneeIfDupontConsumer());
        System.out.println(liste);
        System.out.println("Suppression de la possibilité de rattrapage si type de contrôle est QCM avec une classe interne statique \n");
        liste.getListe().forEach(new staticRattrapageImpossibleSiQcmConsumer());
        System.out.println(liste);
        liste.resetListe();


        System.out.println(" ----------------------------- TRAITEMENT AVEC CLASSES INTERNES NON STATIQUES ----------------------------- \n");
        System.out.println("LISTE INITIALE SANS TRAITEMENT");
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE avec une classe interne non statique \n");
        liste.traitementIncrementationAnneeNonStatic(liste.getListe());
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE si l'enseignant est Dupont avec une classe interne non statique \n");
        liste.traitementIncrementationAnneeIfDupontNonStatic(liste.getListe());
        System.out.println(liste);
        System.out.println("Suppression de la possibilité de rattrapage si type de contrôle est QCM avec une classe interne non statique \n");
        liste.traitementRattrapageImpossibleSiQcmNonStatic(liste.getListe());
        System.out.println(liste);
        liste.resetListe();


        System.out.println(" ----------------------------- TRAITEMENT AVEC CLASSES LOCALES ----------------------------- \n");
        System.out.println("LISTE INITIALE SANS TRAITEMENT");
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE avec classe globale \n");
        liste.traitementIncrementationAnneeLocal(liste.getListe());
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE si l'enseignant est Dupont avec une classe interne non statique \n");
        liste.traitementIncrementationAnneeIfDupontLocal(liste.getListe());
        System.out.println(liste);
        System.out.println("Suppression de la possibilité de rattrapage si type de contrôle est QCM avec une classe interne non statique \n");
        liste.traitementRattrapageImpossibleSiQcmLocal(liste.getListe());
        System.out.println(liste);
        liste.resetListe();

        // CLASSES ANONYMES
        ueList listeAnonyme = new ueList(){
            public void traitementIncrementationAnneeLocal(ArrayList liste){
                liste.forEach(new staticIncrementationAnneeConsumer());
                System.out.println("On affiche ce message grâce à la classe anonyme d'ueList dans la méthode d'incrémentation d'année local \n");
            }
            public void traitementIncrementationAnneeIfDupontLocal(ArrayList liste){
                liste.forEach(new staticIncrementationAnneeIfDupontConsumer());
                System.out.println("On affiche ce message grâce à la classe anonyme d'ueList dans la méthode d'incrémentation d'année si Dupont local \n");
            }
            public void traitementRattrapageImpossibleSiQcmLocal(ArrayList liste){
                liste.forEach(new staticRattrapageImpossibleSiQcmConsumer());
                System.out.println("On affiche ce message grâce à la classe anonyme d'ueList dans la méthode qui rend le rattrapage impossible si QCM local \n");
            }
        };
        System.out.println(" ----------------------------- TRAITEMENT AVEC CLASSES ANONYMES ----------------------------- \n");
        System.out.println("LISTE ANONYME INITIALE SANS TRAITEMENT");
        System.out.println(listeAnonyme);
        System.out.println("Incrementation des années des différentes UE avec classe globale \n");
        listeAnonyme.traitementIncrementationAnneeLocal(listeAnonyme.getListe());
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE si l'enseignant est Dupont avec une classe interne non statique \n");
        listeAnonyme.traitementIncrementationAnneeIfDupontLocal(listeAnonyme.getListe());
        System.out.println(liste);
        System.out.println("Suppression de la possibilité de rattrapage si type de contrôle est QCM avec une classe interne non statique \n");
        listeAnonyme.traitementRattrapageImpossibleSiQcmLocal(listeAnonyme.getListe());
        System.out.println(liste);


        // EXPRESSIONS LAMBDAS
        Consumer<ue> traitementIncrementationAnneeLambda = ue -> {
            ue.anneeDeCreation++;
        };
        Consumer<ue> traitementIncrementationAnneeIfDupontLambda = ue -> {
            if(ue.enseignant == "Dupont") {
                ue.anneeDeCreation++;
            }
        };
        Consumer<ue> traitementRattrapageImpossibleSiQcmLambda = ue -> {
            if(ue.controle == "QCM") {
                ue.rattrapage = false;
            }
        };

        System.out.println(" ----------------------------- TRAITEMENT AVEC EXPRESSIONS LAMBDAS ----------------------------- \n");
        System.out.println("LISTE INITIALE SANS TRAITEMENT");
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE avec expression lambda \n");
        liste.getListe().forEach(traitementIncrementationAnneeLambda);
        System.out.println(liste);
        System.out.println("Incrementation des années des différentes UE si l'enseignant est Dupont avec expression lambda \n");
        liste.getListe().forEach(traitementIncrementationAnneeIfDupontLambda);
        System.out.println(liste);
        System.out.println("Suppression de la possibilité de rattrapage si type de contrôle est QCM avec expression lambda \n");
        liste.getListe().forEach(traitementRattrapageImpossibleSiQcmLambda);
        System.out.println(liste);
        liste.resetListe();
    }
}
