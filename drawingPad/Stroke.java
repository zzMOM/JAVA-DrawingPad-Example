package drawingPad;

import java.util.*;
import java.io.Serializable;
import java.awt.*;

/*
 * Stroke, TwoEndsShape and Text, extends Shape class
 */
public class Stroke extends Shape{
	//The list of points on the stroke
	//The elements are instances of java.awt.Point
	protected ArrayList<Point> points = new ArrayList<Point>();
	protected Color color = Color.black;
	
	public Stroke(){}
	
	public Stroke(Color color){
		this.color = color;
	}
	
	public void addPoint(Point p){
		if(p != null){
			points.add(p);
		}
	}
	
	public ArrayList getPoints(){
		return points;
	}
	
	public void draw(Graphics g){
		if(color != null){
			g.setColor(color);
		}
		Point prev = null;
		Iterator iter = points.iterator(); 
	    while (iter.hasNext()) { 
	    	Point cur = (Point) iter.next(); 
	    	if (prev != null) {
	    		g.drawLine(prev.x, prev.y, cur.x, cur.y); 
	    	}
	    	prev = cur; 
	    }
	}
}