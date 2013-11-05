package draw4;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.net.URL;

import scribble3.*;


public class ImageTool extends AbstractTool implements Tool{
	protected ImageShape image;
	protected String imageName;
	
	public ImageTool(ScribbleCanvas canvas, String name, String imageName){
		super(canvas, name);
		this.imageName = imageName;
	}
	
	@Override
	public void startShape(Point p){
		image = new ImageShape();
		image.setLocation(p.x, p.y);
		image.setName(imageName);
		Graphics g = canvas.getGraphics();
		image.draw(g);
	}
	
	public void addPointToShape(Point p){}
	public void endShape(Point p){}
}
