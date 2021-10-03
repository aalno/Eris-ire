import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PanneauFin extends JPanel {
	
	private File dossierSourceA = new File("src/outro_bd/outroA");
	private File dossierSourceB = new File("src/outro_bd/outroB");
	private File dossierSourceC = new File("src/outro_bd/outroC");
	
	private ArrayList<BufferedImage> caseA = new ArrayList<BufferedImage> ();
	private int quelleFrameA = 0;
	private Timer timerAnimationA;
	private ArrayList<BufferedImage> caseB = new ArrayList<BufferedImage> ();
	private int quelleFrameB = 0;
	private Timer timerAnimationB;
	
	private ArrayList<BufferedImage> listeCases = new ArrayList<BufferedImage> ();
	
	private BufferedImage case_ACTUEL;
	private int quelleCase = 0;
	
	PanneauFin()
	{
		try {
			for (File frame : dossierSourceA.listFiles())
			{
				caseA.add(ImageIO.read(frame));
			}
			for (File frame : dossierSourceB.listFiles())
			{
				caseB.add(ImageIO.read(frame));
			}
			for (File frame : dossierSourceC.listFiles())
			{
				listeCases.add(ImageIO.read(frame));
			}
		} catch (IOException e) {e.printStackTrace();}
		
		timerAnimationA = new Timer(200, new AnimationAListener());
		timerAnimationB = new Timer(70, new AnimationBListener());
		timerAnimationA.start();
		
		this.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked (MouseEvent e)
			{
				if (quelleCase < listeCases.size() + 1)
				{
					if (quelleCase == 0)
					{
						timerAnimationA.stop();
						timerAnimationB.start();
					}
					if (quelleCase == 1)
						timerAnimationB.stop();
					
					quelleCase++;
					if (quelleCase > 1)
						case_ACTUEL = listeCases.get(quelleCase - 2);
					repaint();
				}
			}
		});
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		g.drawImage(case_ACTUEL, 0, 0, this);
	}
	
	private class AnimationAListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			case_ACTUEL = caseA.get(quelleFrameA);
			repaint();
			
			if (quelleFrameA < caseA.size() - 1)
				quelleFrameA++;
			else
				quelleFrameA = 0;
		}
	}
	
	private class AnimationBListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			case_ACTUEL = caseB.get(quelleFrameB);
			repaint();
			
			if (quelleFrameB < caseB.size() - 1)
				quelleFrameB++;
			else
				quelleFrameB = 0;
		}
	}

}
