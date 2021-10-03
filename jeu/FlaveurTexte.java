package jeu;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class FlaveurTexte {
	
	private ArrayList<String> listeLignes = new ArrayList<String> ();
	private ArrayList<Boolean> quelSprite = new ArrayList<Boolean> ();
	private BufferedReader texteLecture;
	private BufferedImage spriteE;
	private BufferedImage spriteI;
	
	public FlaveurTexte()
	{
		try
		{
			texteLecture = new BufferedReader(new FileReader(new File("src/elementsJeu/flaveur_texte.txt")));
			this.assimilationTexte();
			
			spriteE = ImageIO.read(new File("src/elementsJeu/spriteE_mini.png"));
			spriteI = ImageIO.read(new File("src/elementsJeu/spriteI_mini.png"));
		}
		catch (IOException e) {e.printStackTrace();}
	}
	
	private void assimilationTexte() throws IOException
	{
		String ligneLue;
		
		// 1ère ligne à passer
		ligneLue = texteLecture.readLine();
		while (ligneLue != null)
		{
			if (ligneLue.contains("E: "))
				quelSprite.add(true);
			else
				quelSprite.add(false);
			
			if (ligneLue != null) //Après on retire le début
				ligneLue = ligneLue.substring(3);
			listeLignes.add(ligneLue);
			ligneLue = texteLecture.readLine();
		}
	}
	
	public ArrayList<String> getTexte()
	{
		return this.listeLignes;
	}
	
	public BufferedImage getSprite(int numero)
	{
		return (quelSprite.get(numero)) ? this.spriteE : this.spriteI;
	}
}
