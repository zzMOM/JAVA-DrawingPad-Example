package draw6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import scribble3.ScribbleCanvas;
import draw5.*;


public class MultiPointsShapeTool extends scribble3.AbstractTool implements PolygonTool{
	protected int x[], y[];
	protected int index = 0;
	protected ArrayList<Point> xy = new ArrayList<Point>();
	protected MultiPointsShape prototype;
	
	public MultiPointsShapeTool(ScribbleCanvas canvas, String name, String tipText, MultiPointsShape prototype) {
		super(canvas, name, tipText);
		this.prototype = prototype;
	}
	
	public void addPointToArray(Point p){
		Graphics g = canvas.getGraphics();
		g.setXORMode(Color.darkGray);
		g.setColor(Color.lightGray);
		if(xy.isEmpty()){
			prototype.drawOutline(g, p.x, p.y, p.x, p.y);
		} else {
			prototype.drawOutline(g, xy.get(index - 1).x, xy.get(index - 1).y, p.x, p.y);
		}
		xy.add(p);
		index++;
	}
	
	public void endArray(Point p){
		//close the polygon
		Graphics g = canvas.getGraphics();
		prototype.drawOutline(g, xy.get(0).x, xy.get(0).y, xy.get(index - 1).x, xy.get(index - 1).y);
		g.setXORMode(Color.darkGray);
		g.setColor(Color.lightGray);
		prototype.setColor(canvas.getCurColor());
		x = new int[index - 1];
		y = new int[index - 1]; 
		for(int i = 0; i < index - 1; i++){
			x[i] = xy.get(i).x;
			y[i] = xy.get(i).y;
		}
		try {
			MultiPointsShape newShape = (MultiPointsShape) prototype.clone();
			newShape.setX(x);
			newShape.setY(y);
			newShape.setColor(canvas.getCurColor());
			canvas.addShape(newShape);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		//reset the arraylist
	    xy.clear();
	    index = 0;
	    g.setPaintMode();
	    canvas.repaint();
	}
	
	public void startShape(Point p){}		
	public void addPointToShape(Point p){}
	public void endShape(Point p){}
}
