package draw5;

import java.awt.Color;
import java.awt.Graphics;

public abstract class MultiPointsShape extends scribble3.Shape implements Cloneable{
	protected int x[], y[];
	abstract public void drawOutline(Graphics g, int x[], int y[]);
	
	public MultiPointsShape(){}
	
	public MultiPointsShape(Color color){
		super(color);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public void setX(int x[]){
		this.x = x;
	}
	
	public void setY(int y[]){
		this.y = y;
	}
	
	
	public int[] getX(){
		return x;
	}
	
	public int[] getY(){
		return y;
	}
}
