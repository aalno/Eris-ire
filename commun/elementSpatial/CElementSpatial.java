package commun.elementSpatial;

import java.awt.Rectangle;

abstract public class CElementSpatial {
	
	//Ou comment interagir avec de la peinture (élément de base sans dimensions, mais avec location)
	
	//Nature
	protected int posX;
	protected int posY;
	protected int width;
	protected int height;
	protected Rectangle aire;
	
	//Sans position
	CElementSpatial()
	{
		this.posX = 0;
		this.posY = 0;
	}
	
	protected CElementSpatial(int x, int y)
	{
		this.posX = x;
		this.posY = y;
	}
	
	//Accesseurs
	
	public int getX()
	{
		return this.posX;
	}
	
	public int getY()
	{
		return this.posY;
	}
	
	public int getWidth()
	{
		return this.width;
	}
	
	public int getHeight()
	{
		return this.height;
	}
	
	public Rectangle getAire()
	{
		return this.aire;
	}
	
	public void setPos(int x, int y)
	{
		this.posX = x;
		this.posY = y;
		this.aire.setLocation(posX, posY);
	}
	
	public void centrerX(int tailleTotale)
	{
		this.setPos((tailleTotale - this.width)/2, this.posY);
	}
	
	public void centrerY(int tailleTotale)
	{
		this.setPos(this.posX, (tailleTotale - this.height)/2);
	}
}
