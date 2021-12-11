package td4;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Promotion2020 {
    private ArrayList<Etudiant> etudiants = new ArrayList<>();
    private Etudiant rootEtudiant;
    private Adresse rootAdresse;

    public ArrayList<Etudiant> getEtudiantsList(){
        return this.etudiants;
    }

    public void fillEtudiantList(){
        etudiants.add(new Etudiant("Choisy","Sébastien",29,new Date(),"0611119963"));
        etudiants.get(0).setParents(
                new Personne[]{new Personne("choisy", "sony", 58), new Personne("choisy","philippe",60)}
                );
        etudiants.get(0).setAdresse(new Adresse("la colle sur loup","chemin de montmeuille",211,true));
        etudiants.add(new Etudiant("Corbière","Nicolas",22,new Date(),"0611589662"));
        etudiants.get(1).setParents(
                new Personne[]{new Personne("Corbière", "Barbara", 58), new Personne("Corbière","Henry",60)}
        );
        etudiants.get(1).setAdresse(new Adresse("Saint laurent","chemin du puit",14,true));
        etudiants.add(new Etudiant("Neffati","Youssef",23,new Date(),"0645859652"));
        etudiants.get(2).setParents(
                new Personne[]{new Personne("Neffati", "Nadine", 58), new Personne("Neffati","Philippe",60)}
        );
        etudiants.get(2).setAdresse(new Adresse("Saint laurent","chemin de la rose",156,true));
        etudiants.add(new Etudiant("Adams","Pierre",21,new Date(),"0685459565"));
        etudiants.get(3).setParents(
                new Personne[]{new Personne("Adams", "Sylvie", 58), new Personne("Adams","Patrick",60)}
        );
        etudiants.get(3).setAdresse(new Adresse("Biot","chemin des lucioles",89,true));
        etudiants.add(new Etudiant("Hachimi","Youssef",24,new Date(),"0674586235"));
        etudiants.get(4).setParents(
                new Personne[]{new Personne("Hachimi", "Isabelle", 58), new Personne("Hachimi","Gerard",60)}
        );
        etudiants.get(4).setAdresse(new Adresse("Cagnes sur mer","rue pasqualini",452,true));
        etudiants.add(new Etudiant("Parizet","Nicolas",22,new Date(),"0612356545"));
        etudiants.get(5).setParents(
                new Personne[]{new Personne("Parizet", "Nadine", 58), new Personne("Parizet","Philippe",60)}
        );
        etudiants.get(5).setAdresse(new Adresse("Cagnes sur mer","chemin renoir",36,true));
        etudiants.add(new Etudiant("Lemoine","Alexandre",22,new Date(),"0632365685"));
        etudiants.get(6).setParents(
                new Personne[]{new Personne("Lemoine", "Carole", 58), new Personne("Lemoine","Alain",60)}
        );
        etudiants.get(6).setAdresse(new Adresse("Villeneuve loubet","chemin des ferrayones",756,true));
        etudiants.add(new Etudiant("Rethers","Mathieu",23,new Date(),"0674785326"));
        etudiants.get(7).setParents(
                new Personne[]{new Personne("Rethers", "Elodie", 58), new Personne("Rethers","Kevin",60)}
        );
        etudiants.get(7).setAdresse(new Adresse("Saint laurent","chemin de l'hiver",169,true));
        etudiants.add(new Etudiant("Samia","Oussame",21,new Date(),"0665953545"));
        etudiants.get(8).setParents(
                new Personne[]{new Personne("Samia", "Marion", 58), new Personne("Samia","Oussama",60)}
        );
        etudiants.get(8).setAdresse(new Adresse("Saint laurent","chemin de l'abeille",856,true));
        etudiants.add(new Etudiant("Migeat","Thomas",22,new Date(),"0645421463"));
        etudiants.get(9).setParents(
                new Personne[]{new Personne("Migeat", "Nadine", 58), new Personne("Migeat","Gerard",60)}
        );
        etudiants.get(9).setAdresse(new Adresse("Saint laurent","chemin du pigeonnier",189,true));
        this.rootEtudiant = etudiants.get(0);
        this.rootAdresse = etudiants.get(0).getAdresse();
    }

    public Etudiant getRootEtudiant(){
        return this.rootEtudiant;
    }

    public Adresse getRootAdresse(){
        return this.rootAdresse;
    }

    public void saveEtudiants(String file){
        ObjectOutputStream g_etudiant;
        try {
            FileOutputStream support = new FileOutputStream(file);
            g_etudiant = new ObjectOutputStream(support);
            this.etudiants.forEach(etudiant -> {
                try {
                    g_etudiant.writeObject(etudiant);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayEtudiantsList(){
        this.etudiants.forEach(etudiant -> System.out.println(etudiant));
    }

    public void retrieveEtudiants(String file) throws IOException {
        ObjectInputStream g_etudiant;
        try {
            FileInputStream support = new FileInputStream(file);
            g_etudiant = new ObjectInputStream(support);
            for(int i = 0; i < 10 ; i++){
                this.etudiants.add((Etudiant) g_etudiant.readObject());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
        Promotion2020 promo2020 = new Promotion2020();
        promo2020.fillEtudiantList();
        promo2020.saveEtudiants("exercice1Etudiants");
        System.out.println("Liste étudiants après serialization \n");
        System.out.println("Root etudiant :"+ promo2020.getRootEtudiant());
        System.out.println("Root adresse :"+ promo2020.getRootAdresse()+"\n\n");
        promo2020.displayEtudiantsList();
        promo2020.getEtudiantsList().clear();
        promo2020.retrieveEtudiants("exercice1Etudiants");
        System.out.println("Liste étudiants après deserialization \n");
        System.out.println("Root etudiant :"+ promo2020.getRootEtudiant());
        System.out.println("Root adresse :"+ promo2020.getRootAdresse()+"\n\n");
        promo2020.displayEtudiantsList();
    }
}
