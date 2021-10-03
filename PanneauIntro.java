import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import panneauStructure.PanneauType;

public class PanneauIntro extends PanneauType {

	private File dossierSource = new File("src/intro_bd");
	private ArrayList<BufferedImage> listeCases = new ArrayList<BufferedImage> ();
	
	private BufferedImage case_ACTUEL;
	private int quelleCase = 0;
	
	PanneauIntro()
	{
		try {
			for (File frame : dossierSource.listFiles())
			{
				listeCases.add(ImageIO.read(frame));
			}
		} catch (IOException e) {e.printStackTrace();}
		
		case_ACTUEL = listeCases.get(quelleCase);
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked (MouseEvent e)
			{
				if (quelleCase < listeCases.size() - 1)
				{
					quelleCase++;
					case_ACTUEL = listeCases.get(quelleCase);
					repaint();
				}
				else
					PanneauIntro.this.updateObservateur();
			}
		});
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(case_ACTUEL, 0, 0, this);
	}
}
