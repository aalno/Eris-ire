package jeu;

import java.io.File;

import commun.elementSpatial.CESImage;

public class Balle extends CESImage {

	private static File imageSource = new File("src/elementsJeu/balle_mini.png");
	
	private int butX;
	private int butY;
	private int avanceX;
	private int avanceY;
	
	public Balle(int xOrigine, int yOrigine, int x, int y)
	{
		super(xOrigine, yOrigine, imageSource);
		
		this.butX= x;
		this.butY = y;
		avanceX = (this.butX - xOrigine)/20;
		avanceY = (this.butY - yOrigine)/20;
	}
	
	public boolean avance()
	{
		boolean butXAtteint = (this.posX - this.butX > -5 && this.posX - this.butX < 5);
		boolean butYAtteint = (this.posY - this.butY> -5 && this.posY - this.butY < 5);
		
		if (!butXAtteint && !butYAtteint)
			this.setPos(this.posX + avanceX, this.posY + avanceY);
		
		return (!butXAtteint && !butYAtteint);
	}
}
