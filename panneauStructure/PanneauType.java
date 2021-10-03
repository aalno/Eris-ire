package panneauStructure;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;

import javax.swing.JPanel;

import commun.observation.Observable;
import commun.observation.Observateur;

public abstract class PanneauType extends JPanel implements Observable {

	private ArrayList <Observateur> listeObservateurs = new ArrayList <Observateur> ();
	
	protected class FiniListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			PanneauType.this.updateObservateur();
		}
	}
	
	//Observateurs (pour quand l'étape est finie)
	public void addObservateur(Observateur obs)
	{
		listeObservateurs.add(obs);
	}

	public void delObservateur()
	{
		listeObservateurs.clear();
	}

	public void updateObservateur() 
	{
		for (Observateur obs : listeObservateurs)
			obs.update();
	}
}
