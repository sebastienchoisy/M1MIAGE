/* Philippe Lahire 
 * M1 MIAGE: Squelette pour l'exercice 2 A et 2B
 */
package Ex2;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Exercice2 extends JDialog {
	
	// D�claration tableauAffichage (JTextArea)
	//...
	
	private void initInterface() {
	
		 this.setLayout(new FlowLayout());
		//ajouter les boutons
		//....
		//Cr�ation "tableauAffichage " (JTextArea)
		//...
		 JTextArea tableauAffichage = new JTextArea();
		 tableauAffichage.setSize(400,400);    
		 tableauAffichage.setLineWrap(true);
		 tableauAffichage.setEditable(false);
		 tableauAffichage.setVisible(true);
		 JScrollPane scroll = new JScrollPane (tableauAffichage);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		 scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		 
		 JFrame frame = new JFrame ("Exercice 2");
		 frame.setSize(500,500);
		 frame.setResizable(false);
		 frame.setVisible(true);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
		 frame.add(scroll);
		 
		 this.pack();
		 this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		 this.setVisible(true);
	}

	 public static void main(String[] args) {
			
		  }

}
