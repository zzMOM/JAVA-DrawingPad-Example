package draw4;

import java.awt.event.MouseEvent;
import java.net.URL;

import draw1.DrawingCanvas;

public class ImageDrawingCanvasListener extends draw3.KeyboardDrawingCanvasListener{
	protected URL imageSrc;
	
	public ImageDrawingCanvasListener(DrawingCanvas canvas) { 
		super(canvas); 
	}
	
	public void setURL(URL imageSrc){
		this.imageSrc = imageSrc;
	}
	
	public URL getURL(){
		return imageSrc;
	}
	
}
