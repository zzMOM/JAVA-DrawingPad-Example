package draw4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageShape extends scribble3.Shape{
	protected int x, y;
	protected String imageName;
	
	public ImageShape(){}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setName(String imageName){
		this.imageName = imageName;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public String getName(){
		return imageName;
	}
	
	public void draw(Graphics g){
		File imgfile = new File("src/img/" + imageName + ".jpg");
		Image imageOriginal = null;
		try {
			imageOriginal = ImageIO.read(imgfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        g.drawImage(imageOriginal, x, y, null);
	}
}
