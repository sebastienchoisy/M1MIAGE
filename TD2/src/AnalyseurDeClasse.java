
/**
 getname,getsuperclass, get implementedinterface, getdeclaredfields, r�cup�rer les modifiers

 * @author Michel Buffa + modification Philippe Lahire
 * Inspir� par la classe Reflectiontest.java de
 * Cay S. Horstmann & Gary Cornell, publi�e dans le livre Core Java, Sun Press
 */

import java.lang.reflect.*;
import java.io.*;

public class AnalyseurDeClasse {

    public static void analyseClasse(String nomClasse) throws ClassNotFoundException, NoSuchMethodException {
        // R�cup�ration d'un objet de type Class correspondant au nom pass� en param�tres
        Class cl = getClasse(nomClasse);

        afficheEnTeteClasse(cl);
        afficheInnerClasses(cl);
        afficheAttributs(cl);
        afficheConstructeurs(cl);
        afficheMethodes(cl);

        // L'accolade fermante de fin de classe !
        System.out.println("}");


    }


    /** Retourne la classe dont le nom est pass� en param�tre */
    public static Class getClasse(String nomClasse) throws ClassNotFoundException {
        return Class.forName(nomClasse);
    }

    /** Cette m�thode affiche par ex "public class C1 extends C2 implements I1, I2 {" */
    public static void afficheEnTeteClasse(Class cl) {
        //  Affichage du modifier et du nom de la classe
        // CODE A ECRIRE
        String name = cl.getName();
        String modifier = Modifier.toString(cl.getModifiers());


        // R�cup�ration de la superclasse si elle existe (null si cl est le type Object)
        Class supercl = cl.getSuperclass();
        String superclass = "";
        if(supercl != null && supercl.getName() != "Object") {
            superclass = " extends " + supercl.getName();
        }

        Class[] interfaces = cl.getInterfaces();
        String interfacesString = "";
        if(interfaces.length != 0) {
            for (int i = 0; i < interfaces.length; i++) {
                if (i != interfaces.length - 1) {
                    interfacesString = interfacesString + interfaces[i].getName() + ", ";
                } else {
                    interfacesString = interfacesString + interfaces[i].getName() + " ";
                }
            }
        }

        System.out.println(modifier + " class " + name + superclass + " implements " + interfacesString);
        System.out.print(" {\n");
    }

    /** Cette m�thode affiche les classes imbriqu�es statiques ou pas
     A faire apr�s avoir fait fonctionner le reste */
    public static void afficheInnerClasses(Class cl) {
        Class[] innerClasses = cl.getDeclaredClasses();
        System.out.println("InnerClasses: \n");
        if(innerClasses.length != 0){
            for(int i=0;i<innerClasses.length;i++){
                System.out.println(innerClasses[i]+ "\n");
            }
        }
    }

    public static void afficheAttributs(Class cl) {
        Field[] attributs = cl.getDeclaredFields();
        System.out.println("Attributs : \n");
        if(attributs.length != 0){
            for(int i=0;i<attributs.length;i++){
                System.out.println(attributs[i]+ "\n");
            }
        }
    }

    public static void afficheConstructeurs(Class cl) {
        Constructor[] constructors = cl.getConstructors();
        System.out.println("Constructors: \n");
        if(constructors.length != 0){
            for(int i=0;i<constructors.length;i++){
                System.out.println(constructors[i]+ "{}\n");
            }
        }

    }


    public static void afficheMethodes(Class cl) throws NoSuchMethodException {
        Method[] methods = cl.getDeclaredMethods();
        System.out.println("Methodes: \n");
        if(methods.length != 0){
            for(int i=0;i<methods.length;i++){
                System.out.println(methods[i]+ "{}\n");
            }
        }
    }



/* Facultatif au moins dans un premier temps */
/* tester le programme en passant un nom de classe complet en param�tre
     Modifier la m�thode "main" en cons�quence
*/
public static String litChaineAuClavier() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
        }

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


