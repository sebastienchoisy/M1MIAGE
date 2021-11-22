@miageBasics(nom = "nom", prenom = "prenom", annee = 2020, module = "java", seanceTD = 1)
public class ue {
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2020, module = "java", seanceTD = 1)
    String module;
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2020, module = "java", seanceTD = 1)
    Integer anneeDeCreation;
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2020, module = "java", seanceTD = 1)
    String diplome;
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2021, module = "java", seanceTD = 2)
    enseignant enseignant;
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2021, module = "java", seanceTD = 2)
    String controle;
    @miageBasics(nom = "Choisy", prenom = "Sébastien", annee = 2021, module = "java", seanceTD = 2)
    Boolean rattrapage;

    @miageAdvanced(etatCompletudeImplem = draft.finalisé,etatTest = true,etatAutomatisation = true)
    public ue(String module, Integer anneeDeCreation, String diplome, enseignant enseignant, String controle, Boolean rattrapage) {
        this.module = module;
        this.anneeDeCreation = anneeDeCreation;
        this.diplome = diplome;
        this.enseignant = enseignant;
        this.controle = controle;
        this.rattrapage = rattrapage;
    }
}
