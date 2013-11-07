package draw5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import scribble3.ScribbleCanvas;
import draw1.TwoEndsShape;

public class PolygonShapeTool extends scribble3.AbstractTool implements scribble3.Tool{
	protected int x[], y[];
	protected int index = 0;
	protected PolygonShape curPolygon = new PolygonShape();
	
	public PolygonShapeTool(ScribbleCanvas canvas, String name) {
		super(canvas, name);
	}
	
	public void startShape(Point p){}
	
	/*public void startShape(Point p){
		x[0] = canvas.x = p.x;
		y[0] = canvas.y = p.y;
		Graphics g = canvas.getGraphics();
		g.setXORMode(Color.darkGray);
		g.setColor(Color.lightGray);
		curPolygon.drawOutline(g, x, y);
		index++;
	}*/
	
	@Override
	public void addPointToShape(Point p){
		Graphics g = canvas.getGraphics();
		g.setXORMode(Color.darkGray);
		g.setColor(Color.lightGray);
		x[index] = p.x;
		y[index] = p.y;
		curPolygon.drawOutline(g, x, y);
		index++;
	}
	
	@Override
	public void endShape(Point p){
		canvas.mouseButtonDown = false;
		//close the polygon
		Graphics g = canvas.getGraphics();
		g.setXORMode(Color.darkGray);
		g.setColor(Color.lightGray);
		x[index] = p.x;
		y[index] = p.y;
		curPolygon.drawOutline(g, x, y);
		curPolygon.setColor(canvas.getCurColor());
		curPolygon.setX(x);
		curPolygon.setY(y);
		canvas.addShape(curPolygon);
	    g.setPaintMode();
	    canvas.repaint();
	}
}
