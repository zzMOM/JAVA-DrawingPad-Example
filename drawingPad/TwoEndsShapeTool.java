package drawingPad;

import java.awt.*;

public class TwoEndsShapeTool extends AbstractTool implements Tool{
	protected int xStart, yStart;
	protected TwoEndsShape prototype;
	
	public TwoEndsShapeTool(ScribbleCanvas canvas, String name, TwoEndsShape prototype){
		super(canvas, name);
		this.prototype = prototype;
	}
	
	public void startShape(Point p){
		if(prototype != null){
			canvas.mouseButtonDown = true;
			xStart = canvas.x = p.x;
			yStart = canvas.y = p.y;
			Graphics g = canvas.getGraphics();
			g.setXORMode(Color.darkGray);
			g.setColor(Color.lightGray);
			prototype.drawOutline(g, xStart, yStart, xStart, yStart);
		}
	}
	
	public void addPointToShape(Point p){
		if(prototype != null && canvas.mouseButtonDown){
			Graphics g = canvas.getGraphics();
			g.setXORMode(Color.darkGray);
			g.setColor(Color.lightGray);
			prototype.drawOutline(g, xStart, yStart, canvas.x, canvas.y);
			prototype.drawOutline(g, xStart, yStart, p.x, p.y);
		}
	}
	
	public void endShape(Point p){
		canvas.mouseButtonDown = false;
		if(prototype != null){
			try{
				TwoEndsShape newShape = (TwoEndsShape) prototype.clone(); 
				newShape.setColor(canvas.getCurColor());
				newShape.setEnds(xStart, yStart, p.x, p.y); 
				canvas.addShape(newShape);
			} catch (CloneNotSupportedException e) {}
		      Graphics g = canvas.getGraphics();
		      g.setPaintMode();
		      canvas.repaint();
		}
	}
}
