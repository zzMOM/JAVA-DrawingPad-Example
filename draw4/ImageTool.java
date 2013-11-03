package draw4;

import java.awt.Graphics;
import java.awt.Point;
import java.io.File;
import java.net.URL;

import scribble3.*;


public class ImageTool extends AbstractTool implements Tool{
	protected ImageShape image;
	protected File imageSrc;
	
	public ImageTool(ScribbleCanvas canvas, String name){
		super(canvas, name);
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
