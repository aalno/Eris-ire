import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import commun.observation.Observateur;
import jeu.PanneauJeu;
import panneauStructure.PanneauExemple;
import panneauStructure.PanneauType;

public class Fenetre extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Panneaux...
	private CardLayout cl = new CardLayout();
	private JPanel panCL = new JPanel(cl); //contient tout
	
	private PanneauType Pintro = new PanneauIntro();
	private PanneauType Pjeu = new PanneauJeu();
	private JPanel Pfin = new PanneauFin();
	
	Fenetre()
	{
		this.setSize(900, 620);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.misePanneaux();
		this.setContentPane(panCL);
		
		this.setVisible(true);
	}
	
	private void ajoutObservateur(PanneauType quelPanneau, String versQuoi)
	{
		quelPanneau.addObservateur(new Observateur()
		{
			@Override
			public void update()
			{
				cl.show(panCL, versQuoi);
			}
		});
	}
	
	private void misePanneaux()
	{
		this.ajoutObservateur(Pintro, "jeu");
		this.ajoutObservateur(Pjeu, "fin");
		
		panCL.add(Pintro, "intro");
		panCL.add(Pjeu, "jeu");
		panCL.add(Pfin, "fin");
	}
}
