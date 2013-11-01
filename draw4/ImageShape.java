package draw4;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class ImageShape extends scribble3.Shape{
	protected int x, y;
	protected URL imageSrc;
	
	public ImageShape(){}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public void setURL(URL imageSrc){
		this.imageSrc = imageSrc;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public URL getURL(){
		return imageSrc;
	}
	
	public void draw(Graphics g){
		BufferedImage bi;
		try {
            BufferedImage img = ImageIO.read(imageSrc);
            int w = img.getWidth(null);
            int h = img.getHeight(null);
            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            g = bi.getGraphics();
            g.drawImage(img, x, y, null);

        } catch (IOException e) {
            System.out.println("Image could not be read");
//            System.exit(1);
        }
	}
}
