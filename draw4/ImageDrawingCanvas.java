package draw4;

import java.net.URL;

public class ImageDrawingCanvas extends draw3.KeyboardDrawingCanvas{
	protected ImageDrawingCanvasListener imageDrawingCanvasListener;
	
	public ImageDrawingCanvas(){}
	
	public void setURL(URL imageSrc){
		imageDrawingCanvasListener.setURL(imageSrc);
	}
	
	public URL getURL(){
		return imageDrawingCanvasListener.getURL();
	}
}
