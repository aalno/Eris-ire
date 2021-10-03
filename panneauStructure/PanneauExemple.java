package panneauStructure;
import javax.swing.JButton;
import javax.swing.JLabel;

public class PanneauExemple extends PanneauType {
	
	private JButton bouton = new JButton("Suivant");
	private JLabel ou = new JLabel(" ");
	
	public PanneauExemple(String str)
	{
		super();
		
		ou.setText(str);
		this.add(ou);
		
		bouton.addActionListener(new FiniListener());
		this.add(bouton);
	}

}
