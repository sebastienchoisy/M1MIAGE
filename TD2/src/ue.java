public class ue {
    String module;
    Integer anneeDeCreation;
    String diplome;
    String enseignant;
    String controle;
    Boolean rattrapage;

    public ue(String module, Integer anneeDeCreation, String diplome, String enseignant, String controle, Boolean rattrapage) {
        this.module = module;
        this.anneeDeCreation = anneeDeCreation;
        this.diplome = diplome;
        this.enseignant = enseignant;
        this.controle = controle;
        this.rattrapage = rattrapage;
    }
}
