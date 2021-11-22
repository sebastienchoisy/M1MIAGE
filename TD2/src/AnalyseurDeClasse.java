
/**
 getname,getsuperclass, get implementedinterface, getdeclaredfields, r�cup�rer les modifiers

 * @author Michel Buffa + modification Philippe Lahire
 * Inspir� par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publi�e dans le livre Core Java, Sun Press
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.io.*;
import java.util.ArrayList;

@miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
public class AnalyseurDeClasse {
    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void analyseClasse(String nomClasse) throws ClassNotFoundException, NoSuchMethodException {
        // R�cup�ration d'un objet de type Class correspondant au nom pass� en param�tres
        Class cl = getClasse(nomClasse);
        afficheClassAnnotations(cl);
        afficheEnTeteClasse(cl);
        afficheInnerClasses(cl);
        afficheAttributs(cl);
        afficheConstructeurs(cl);
        afficheMethodes(cl);
        getClassAnnotation2020(cl);
        getInnerClassesAnnotation2020(cl);
        DisplayMethodsCreatedIn2020(cl);



        // L'accolade fermante de fin de classe !
        System.out.println("}");


    }
    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    public class Class2 {
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2019, module = "java", seanceTD = 1)
    public class Class3 {
    }




    /** Retourne la classe dont le nom est pass� en param�tre */
    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static Class getClasse(String nomClasse) throws ClassNotFoundException {
        return Class.forName(nomClasse);
    }

    /** Cette m�thode affiche par ex "public class C1 extends C2 implements I1, I2 {" */
    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void afficheEnTeteClasse(Class cl) {
        //  Affichage du modifier et du nom de la classe
        // CODE A ECRIRE
        String name = cl.getName();
        String modifier = Modifier.toString(cl.getModifiers());


        // Recuperation de la superclasse si elle existe (null si cl est le type Object)
        Class supercl = cl.getSuperclass();
        String superclass = "";
        if(supercl != null && supercl.getName() != "Object") {
            superclass = " extends " + supercl.getName();
        }

        Class[] interfaces = cl.getInterfaces();
        String interfacesString = "";
        System.out.println(modifier + " class " + name + superclass);
        if(interfaces.length != 0) {
            for (int i = 0; i < interfaces.length; i++) {
                if (i != interfaces.length - 1) {
                    interfacesString = interfacesString + interfaces[i].getName() + ", ";
                } else {
                    interfacesString =  interfacesString + interfaces[i].getName() + " ";
                }
            }
            System.out.println(" implements " + interfacesString);
        }

        System.out.print(" {\n");
    }

    /** Cette m�thode affiche les classes imbriqu�es statiques ou pas
     A faire apr�s avoir fait fonctionner le reste */
    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void afficheInnerClasses(Class cl) throws NoSuchMethodException, ClassNotFoundException {
        Class[] innerClasses = cl.getDeclaredClasses();

        if(innerClasses.length != 0){
            System.out.println("InnerClasses: \n");
            for(int i=0;i<innerClasses.length;i++){
                System.out.println(innerClasses[i]+ "\n");
               analyseClasse(innerClasses[i].getName());
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2021, module = "java", seanceTD = 2)
    @miageAdvanced(etatCompletudeImplem = draft.complet)
    public static void afficheClassAnnotations(Class cl) {
        Annotation[] annotations = cl.getDeclaredAnnotations();
        if(annotations.length != 0){
            System.out.println("Annotations de la classe " +cl.getName()+ " : \n");
            for(int i=0;i< annotations.length;i++){
                System.out.println(annotations[i]+ "\n");
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2021, module = "java", seanceTD = 2)
    @miageAdvanced(etatCompletudeImplem = draft.complet)
    public static void afficheFieldAnnotations(Field fl) {
        Annotation[] annotations = fl.getDeclaredAnnotations();
        if(annotations.length != 0){
            System.out.println("Annotations du champ "+fl.getName()+ " : \n");
            for(int i=0;i< annotations.length;i++){
                System.out.println(annotations[i]+ "\n");
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2021, module = "java", seanceTD = 2)
    @miageAdvanced(etatCompletudeImplem = draft.complet)
    public static void afficheMethodAnnotations(Method mtd) {
        Annotation[] annotations = mtd.getDeclaredAnnotations();
        if(annotations.length != 0){
            System.out.println("Annotations de la méthode "+mtd.getName()+ " : \n");
            for(int i=0;i< annotations.length;i++){
                System.out.println(annotations[i]+ "\n");
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2021, module = "java", seanceTD = 2)
    @miageAdvanced(etatCompletudeImplem = draft.complet)
    public static void afficheConstructorAnnotations(Constructor cstr) {
        Annotation[] annotations = cstr.getDeclaredAnnotations();
        if(annotations.length != 0){
            System.out.println("Annotations du constructor "+cstr.getName()+ " : \n");
            for(int i=0;i< annotations.length;i++){
                System.out.println(annotations[i]+ "\n");
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void afficheAttributs(Class cl) {
        Field[] attributs = cl.getDeclaredFields();
        System.out.println("Attributs : \n");
        if(attributs.length != 0){
            for(int i=0;i<attributs.length;i++){
                System.out.println(attributs[i]+ "\n");
                afficheFieldAnnotations(attributs[i]);
            }
        }
    }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2019, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void afficheConstructeurs(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        if(constructors.length != 0){
            System.out.println("Constructors: \n");
            for(int i=0;i<constructors.length;i++){
                System.out.println(constructors[i]+ "{}\n");
                afficheConstructorAnnotations(constructors[i]);
            }
        }

    }


    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2019, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void afficheMethodes(Class cl) throws NoSuchMethodException {
        Method[] methods = cl.getDeclaredMethods();
        if(methods.length != 0){
            System.out.println("Methodes: \n");
            for(int i=0;i<methods.length;i++){
                System.out.println(methods[i]+ "{}\n");
                afficheMethodAnnotations(methods[i]);
            }
        }
    }

    public static void getClassAnnotation2020(Class cl){
       miageBasics advancedAnnotations[] = (miageBasics[]) cl.getAnnotationsByType(miageBasics.class);
        for(int i = 0; i < advancedAnnotations.length; i++){
            System.out.println("Annotation de la classe "+cl+" crée en 2020 :\n");
            if(advancedAnnotations[i].annee() == 2020){
                System.out.println(advancedAnnotations[i]);
            }
        }

    }

    public static void DisplayMethodsCreatedIn2020(Class cl) {
        Method[] methods = cl.getDeclaredMethods();
        ArrayList<Method> methods2020 = new ArrayList<>();
        for(int i = 0; i < methods.length; i++){
            miageBasics[] methodAnnotations = methods[i].getAnnotationsByType(miageBasics.class);
            for(int j = 0;j < methodAnnotations.length; j++){
                if(methodAnnotations[j].annee() == 2020){
                    methods2020.add(methods[i]);
                }
            }
        }
        if(methods2020.size()>0){
            System.out.println("Voici les méthodes crées en 2020 : \n");
            for(int h = 0; h < methods2020.size(); h++){
                System.out.println(methods2020.get(h).getName() + "\n");
            }
        }
    }

    public static void getInnerClassesAnnotation2020(Class cl) {
        Class[] innerClasses = cl.getDeclaredClasses();
        ArrayList<miageBasics> advancedAnnotations2020  = new ArrayList<>();

        if(innerClasses.length != 0){
            for(int i=0;i<innerClasses.length;i++) {
                getClassAnnotation2020(innerClasses[i]);
            }
        }

    }

    /* System.out.println("Liste des annotations de classes de la classe "+ cl.getName() +" de l'année 2020 : \n");
        for (int h = 0;h < advancedAnnotations2020.size(); h++){
            System.out.println(advancedAnnotations2020.get(h) + "\n");
        }*/

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2021, module = "java", seanceTD = 2)
    @miageAdvanced(etatCompletudeImplem = draft.complet)
    public static String litChaineAuClavier() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
        }

    @miageBasics(nom = "Choisy", prenom = "Sebastien", annee = 2020, module = "java", seanceTD = 1)
    @miageAdvanced(etatCompletudeImplem = draft.complet, etatTest = true, etatAutomatisation = true)
    public static void main(String[] args) throws Exception {
        boolean ok = false;
        while (!ok) {
            try {
                System.out.print("Entrez le nom d'une classe (ex : java.util.Date): ");
                String nomClasse = litChaineAuClavier();

                analyseClasse(nomClasse);
                ok = true;
            } catch (ClassNotFoundException e) {
                System.out.println("Classe non trouv�e.");
            } catch (IOException e) {
                System.out.println("Erreur d'E/S!");
            }
        }
}
}


