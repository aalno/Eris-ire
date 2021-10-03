package commun.elementSpatial;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CESImage extends CElementSpatial {
	
	//Nature
	private BufferedImage sprite;
	protected BufferedImage[] plusieursSprites;
	
	
	//CONSTRUCTEURS
	
	//Sans Location
	public CESImage(File imgsource)
	{
		super();
		this.creationUneImage(imgsource);
	}
	
	public CESImage(File[] imgsources)
	{
		super();
		this.creationPlusieursImages(imgsources);
	}
	
	//Avec Location
	public CESImage(int x, int y, File imgsource)
	{
		super(x, y);
		this.creationUneImage(imgsource);
	}
	
	public CESImage(int x, int y, File[] imgsources)
	{
		super(x, y);
		this.creationPlusieursImages(imgsources);
	}
	
	
	//MISE EN PLACE
	
	//Une image
	private void creationUneImage(File source)
	{
		try {
			this.sprite = ImageIO.read(source);
		} catch (IOException e) {e.printStackTrace();}
		
		this.width = sprite.getWidth();
		this.height = sprite.getHeight();
		this.aire = new Rectangle(posX, posY, width, height);
	}
	
	//Plusieurs images (MÊME TAILLE)
	private void creationPlusieursImages(File[] sources)
	{
		this.plusieursSprites = new BufferedImage[sources.length];
		
		try {
			for (int i = 0; i < sources.length; i++)
			this.plusieursSprites[i] = ImageIO.read(sources[i]);
		} catch (IOException e) {e.printStackTrace();}
		
		this.width = plusieursSprites[0].getWidth();
		this.height = plusieursSprites[0].getHeight();
		this.aire = new Rectangle(posX, posY, width, height);
	}
	
	
	//ACCESSEURS
	
	public BufferedImage getSprite()
	{
		return this.sprite;
	}
	
	public BufferedImage[] getPlusieursSprites()
	{
		return this.plusieursSprites;
	}
}
