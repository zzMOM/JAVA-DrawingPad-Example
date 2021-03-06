package scribble3;

import java.awt.*;
import java.io.Serializable;

/**
 * To draw a shape includes two parts: color and draw;
 * set color is the same for all different shapes
 * but draw method is different.
 * Set an abstract class shape with the same color setting but different abstract draw method
 * @author weiwu
 *
 */
public abstract class Shape implements Serializable { 

	  public Shape() {} 
	  
	  public Shape(Color color) {
	    this.color = color; 
	  } 
	
	  public void setColor(Color color) {
	    this.color = color; 
	  } 
	
	  public Color getColor() {
	    return color; 
	  }
	
	  //draw method will be specified in each different shape.
	  public abstract void draw(Graphics g); 
	
	  protected Color color = Color.black; 

}

