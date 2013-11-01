package draw4;

import java.awt.Graphics;
import java.awt.Point;
import java.net.URL;

import scribble3.*;


public class ImageTool extends AbstractTool{
	protected ImageShape image;
	protected URL imageSrc;
	
	public ImageTool(ScribbleCanvas canvas, String name, URL imageSrc){
		super(canvas, name);
		this.imageSrc = imageSrc;
	}
	
	public void startShape(Point p){
		image = new ImageShape();
		image.setLocation(p.x, p.y);
		image.setURL(imageSrc);
		Graphics g = canvas.getGraphics();
		image.draw(g);
	}
	
	public void addPointToShape(Point p){}
	public void endShape(Point p){}
}
