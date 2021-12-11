package td4;

import java.io.IOException;
import java.util.Date;

public class Etudiant extends Personne {
	
	private Date datePremiereInscription;
	transient private String numTel;
	Personne[] parents;


	public Etudiant() {
		System.out.println("Constructeur par d�faut d_�tudiant");
	}

	public Etudiant(String n, String p, int a, Date d, String t) {
		super(n, p, a);
		datePremiereInscription = d;
		numTel = t;
	}

	private void writeObject(java.io.ObjectOutputStream out)
			throws IOException {
		out.writeObject(super.getAdresse());
	}

	private void readObject(java.io.ObjectInputStream in)
			throws IOException, ClassNotFoundException {
		super.setAdresse((Adresse) in.readObject());
	}

	public Personne[] getParents() {
		return parents;
	}

	public void setParents(Personne[] parents) {
		this.parents = parents;
	}

	public Date getDatePremiereInscription() {
		return datePremiereInscription;
	}

	public void setDatePremiereInscription(Date datePremiereInscription) {
		this.datePremiereInscription = datePremiereInscription;
	}

	public String getNumTel() {
		return numTel;
	}

	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}

	public String toString(){
		return "L'étudiant "+ super.getNom() + " " + super.getPrenom() + "\n" + "age : " + super.getAge()
				+ " " + super.getAdresse() + "\n";
	}

}
