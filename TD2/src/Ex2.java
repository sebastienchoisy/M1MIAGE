

import java.lang.reflect.Field;


public class Ex2 {


    static void toString(Object obj) throws Exception {

        Field[] fields = obj.getClass().getDeclaredFields();
        System.out.println(obj.getClass());

        if(fields.length == 0){
            System.out.println("no field");
        }
        System.out.println("Pour cette instance de "+obj.getClass()+", les valeurs des champs sont :\n");
        for (int i = 0; i < fields.length; i++) {

            Object value = fields[i].get(obj);

            System.out.println("La valeur de " + fields[i].getName() + " est " + value);
        }
    }



    public static void main(String[] args) throws Exception {
        ue ueTest = new ue("français",1654,"M1",new enseignant("dupont",1964),"qcm",true);
        toString(ueTest);
    }
}
