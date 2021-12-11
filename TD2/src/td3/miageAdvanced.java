package td3;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface miageAdvanced {

    public draft etatCompletudeImplem() default draft.partiel;
    public boolean etatTest() default false;
    public boolean etatAutomatisation() default false;
}
