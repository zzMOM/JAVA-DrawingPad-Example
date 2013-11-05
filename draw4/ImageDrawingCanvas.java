package draw4;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.EventListener;

public class ImageDrawingCanvas extends draw3.KeyboardDrawingCanvas{
	protected ImageDrawingCanvasListener imageDrawingCanvasListener;
	protected Image imageOriginal;
	
	public ImageDrawingCanvas(){}
	
	protected EventListener makeCanvaslistener(){
		return(drawingCanvasListener = new ImageDrawingCanvasListener(this));
	}
	
	public void setImage(Image imageOriginal){
		this.imageOriginal = imageOriginal;
	}
	
	public Image getImage(){
		return imageOriginal;
	}
}
