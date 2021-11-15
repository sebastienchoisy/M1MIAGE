/* Philippe Lahire 
 * M1 MIAGE: Squelette pour l'exercice 2 A et 2B
 */
package Ex2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Ex1.ueList;

public class Exercice2 extends JDialog {
	
	// D�claration tableauAffichage (JTextArea)
	//...
	
	private void initInterface() {


	
		 this.setLayout(new FlowLayout());
		ueList liste = new ueList();
		liste.remplirListe();


		//Cr�ation "tableauAffichage " (JTextArea)
		//...
		 JTextArea tableauAffichage = new JTextArea();
		 tableauAffichage.setSize(400,400);
		 tableauAffichage.setLineWrap(true);
		 tableauAffichage.setEditable(false);
		 tableauAffichage.setVisible(true);
		 tableauAffichage.setText(liste.getListe().toString());
		 JScrollPane scroll = new JScrollPane (tableauAffichage);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		AbstractAction traitement1 = new AbstractAction() {
			{
				putValue( Action.NAME, "Ajouter une année");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				liste.traitementIncrementationAnneeLocal(liste.getListe());
				tableauAffichage.setText(liste.getListe().toString());
			}
		};

		AbstractAction traitement2 = new AbstractAction() {
			{
				putValue( Action.NAME, "Ajouter une année si Dupont");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				liste.traitementIncrementationAnneeIfDupontLocal(liste.getListe());
				tableauAffichage.setText(liste.getListe().toString());
			}
		};

		AbstractAction traitement3 = new AbstractAction() {
			{
				putValue( Action.NAME, "pas de rattrapage si qcm");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				liste.traitementRattrapageImpossibleSiQcmLocal(liste.getListe());
				tableauAffichage.setText(liste.getListe().toString());
			}
		};

		JButton Traitement1 = new JButton(traitement1);
		JButton Traitement2 = new JButton(traitement2);
		JButton Traitement3 = new JButton(traitement3);
		Traitement1.setBounds(10,400,140,40);
		Traitement2.setBounds(150,400,140,40);
		Traitement3.setBounds(290,400,140,40);



		 
		 JFrame frame = new JFrame ("Exercice 2");
		 frame.setSize(700,700);
		 frame.setResizable(true);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.add(Traitement1);
		 frame.add(Traitement2);
		 frame.add(Traitement3);
		    
		 frame.add(scroll);
		 
		 this.pack();
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setVisible(true);

	}

	 public static void main(String[] args) {
			Exercice2 exercice = new Exercice2();
			exercice.initInterface();
		  }

}
