package jeu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;

import panneauStructure.PanneauType;

public class PanneauJeu extends PanneauType{
	
	private int origineX = 450;
	private int origineY = 0;
	private ArrayList<Balle> listeBalles = new ArrayList<Balle> ();
	private ArrayList<Gens> listeGens = new ArrayList<Gens> ();
	
	private BufferedImage fond;
	
	private Rectangle envergureBazooka = new Rectangle(origineX - 105, 0, 210, 110);
	private BufferedImage bazooka;
	
	private Clip clip; //cri
	private int points = 0;
	private FlaveurTexte dialogue = new FlaveurTexte();
	private String ligneActuelle = new String(" ");
	private BufferedImage spriteActuel;
	private BufferedImage barreDialogue;
	
	private Timer timer;
	
	private MouseAdapter sourisAdapter;
	private int mouseX = origineX;
	private int mouseY = 300;
	
	public PanneauJeu()
	{
		this.miseEnPlaceSouris();
		
		try
		{
			fond = ImageIO.read(new File("src/elementsJeu/fond.png"));
			bazooka = ImageIO.read(new File("src/elementsJeu/bazooka_mini.png"));
			barreDialogue = ImageIO.read(new File("src/elementsJeu/barre_dialogue_mini.png"));
		} catch (IOException e) {e.printStackTrace();}
		
		timer = new Timer(20, new TempsListener());
		timer.start();
	}
	
	public void paintComponent (Graphics g)
	{
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D) g;
		
		//Fond
		g2d.drawImage(fond, 0, 0, this);
		
		//Gens
		for (Gens gens : listeGens)
		g2d.drawImage(gens.getSprite(), gens.getX(), gens.getY(), this);
		
		//Ecran sombre
		if (points > 48)
		{
			g2d.setColor(new Color(0, 0, 0, 100));
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		}
		
		//Balles
		for (Balle balle : listeBalles)
		g2d.drawImage(balle.getSprite(), balle.getX(), balle.getY(), this);
		
		//espace dialogues
		if (spriteActuel != null)
		{
			g2d.drawImage(barreDialogue, 175, 440, this);
			g2d.drawImage(spriteActuel, 125, 440, this);
			Font font = new Font("Courier", Font.BOLD, 14);
			g2d.setFont(font);
			g2d.drawString(ligneActuelle, 230, 475);
		}
		
		//Bazooka
		double angle = Math.atan2(origineY - mouseY, origineX - mouseX) - Math.PI / 2;
		g2d.rotate(angle, origineX, origineY);
		g2d.drawImage(bazooka, origineX - 50, -100, this);
	}
	
	private void miseEnPlaceSouris()
	{
		this.addMouseListener(sourisAdapter = new MouseAdapter()
		{
			public void mouseClicked (MouseEvent e)
			{
				mouseX = e.getX();
				mouseY = e.getY();
				
				listeBalles.add(new Balle(origineX, origineY, mouseX, mouseY));
				
				repaint(envergureBazooka); //Inclut nouvelle balle
			}
		});
	}
	
	private void cri() throws LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		clip = AudioSystem.getClip();
		AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File("src/elementsJeu/aou.wav"));
		clip.open(inputStream);
		clip.start();
	}
	
	private void quandTouche()
	{
		try
		{
			this.cri();
		} catch (LineUnavailableException | UnsupportedAudioFileException | IOException e)
		{e.printStackTrace();}
		
		clip.start();
		
		points++;
		if (points > 48)
			repaint();
		
		if (points < dialogue.getTexte().size())
		{
			ligneActuelle = dialogue.getTexte().get(points);
			spriteActuel = dialogue.getSprite(points);
			repaint(125, 440, 650, 100);
		}
		else
			this.FINI();
	}
	
	private void FINI()
	{
		this.removeMouseListener(sourisAdapter);
		this.updateObservateur();
	}
	
	private class TempsListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			ArrayList<Balle> ballesARetirer = new ArrayList<Balle> ();
			ArrayList<Gens> gensARetirer = new ArrayList<Gens> ();
			
			// Générer nouveau gens
			int hasard = (int) (Math.random()*20);
			if (hasard == 0)
			{
				Gens nouveauGens = new Gens();
				listeGens.add(nouveauGens);
				repaint(nouveauGens.getAire());
			}
			
			for (Gens gens : listeGens)
			{
				//Gens avance
				repaint(gens.getAire());
				
				if (gens.avance())
					repaint(gens.getAire());
				else
					gensARetirer.add(gens);
			}
			
			for (Balle balle : listeBalles)
			{
				//Balle avance
				repaint(balle.getAire());
				
				if (balle.avance())
					repaint(balle.getAire());
				else
					ballesARetirer.add(balle);
				
				// Contact
				for (Gens gens : listeGens)
				{
					int marge = 10;
					Rectangle aireGensEmarge = new Rectangle(gens.getX() - marge, gens.getY() - marge, gens.getWidth() + 2*marge, gens.getHeight() + 2*marge);
					if (aireGensEmarge.contains(balle.getAire()))
					{
						if (!gensARetirer.contains(gens))
							gensARetirer.add(gens);
						if (!ballesARetirer.contains(balle))
							ballesARetirer.add(balle);
						
						quandTouche();
					}
				}
			}
			
			listeBalles.removeAll(ballesARetirer);
			listeGens.removeAll(gensARetirer);
		}
	}
}
