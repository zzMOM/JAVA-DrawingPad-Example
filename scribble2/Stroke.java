package scribble2;

import java.util.*;
import java.io.Serializable;
import java.awt.*;

public class Stroke implements Serializable{
	//The list of points on the stroke
	//The elements are instances of java.awt.Point
	protected ArrayList points = new ArrayList();
	protected Color color = Color.black;
	
	public Stroke(){}
	
	public Stroke(Color color){
		this.color = color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void addPoint(Point p){
		if(p != null){
			points.add(p);
		}
	}
	
	public ArrayList getPoints(){
		return points;
	}
}
