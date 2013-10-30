package drawingPad;

import java.awt.*;

/*
 * Stroke, TwoEndsShape and Text, extends Shape class.
 * TwoEndsShape
 * but it has a new abstract common method drawOutline for all classes extends TwoEndsShape
 * and it includes several new common methods for all classes extends TwoEndsShape
 * 
 * all the subclasses of TweEndsShape must include two function draw() and drawOutline()
 */
public abstract class TwoEndsShape extends Shape implements Cloneable{
	protected int x1, x2, y1, y2;
	abstract public void drawOutline(Graphics g, int x1, int y1, int x2, int y2);
	
	public TwoEndsShape(){}
	
	public TwoEndsShape(Color color){
		super(color);
	}
	
	public Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
	public void setEnds(int x1, int y1, int x2, int y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void setEnd1(int x1, int y1){
		this.x1 = x1;
		this.y1 = y1;
	}
	
	public void setEnd2(int x2, int y2){
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public int getX1(){
		return x1;
	}
	
	public int getX2(){
		return x2;
	}
	
	public int getY1(){
		return y1;
	}
	
	public int getY2(){
		return y2;
	}
	
}

