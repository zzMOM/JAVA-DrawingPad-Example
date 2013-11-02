package draw4;

import java.io.File;
import java.net.URL;
import java.util.EventListener;

public class ImageDrawingCanvas extends draw3.KeyboardDrawingCanvas{
	protected ImageDrawingCanvasListener imageDrawingCanvasListener;
	protected File imageSrc;
	
	public ImageDrawingCanvas(){}
	
	protected EventListener makeCanvaslistener(){
		return(drawingCanvasListener = new ImageDrawingCanvasListener(this));
	}
	
	public void setURL(File imageSrc){
		this.imageSrc = imageSrc;
	}
	
	public File getURL(){
		return imageSrc;
	}
}
