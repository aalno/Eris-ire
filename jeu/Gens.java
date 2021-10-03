package jeu;

import java.io.File;

import commun.elementSpatial.CESImage;

public class Gens extends CESImage {
	
	private static File imageSource = new File("src/elementsJeu/gens1_mini.png");
	
	private int departX;
	private int butX;
	private int butY;
	private int avanceX;
	private int avanceY;
	
	public Gens()
	{
		super(imageSource);
		
		int hasardSens = (int) (Math.random()*10);
		this.departX = (hasardSens < 5) ? 0 : 900;
		this.butX = (hasardSens < 5) ? 900 : 0 - this.width;
		
		this.setPos(departX, (int) (Math.random()*(600 - 120) + 120));
		this.butY = (int) (Math.random()*(600 - 120) + 120);
		
		avanceX = (this.butX - this.posX)/200;
		avanceY = (this.butY - this.posY)/200;
	}
	
	public boolean avance()
	{
		boolean butXAtteint = (this.posX - this.butX > -10 && this.posX - this.butX < 10);
		boolean butYAtteint = (this.posY - this.butY> -10 && this.posY - this.butY < 10);
		
		if (!butXAtteint && !butYAtteint)
			this.setPos(this.posX + avanceX, this.posY + avanceY);
		
		return (!butXAtteint && !butYAtteint);
	}

}
