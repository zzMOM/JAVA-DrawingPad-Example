package draw4;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;

import draw1.DrawingCanvas;

public class ImageDrawingCanvasListener extends draw3.KeyboardDrawingCanvasListener{
	protected File imageSrc;
	
	public ImageDrawingCanvasListener(DrawingCanvas canvas) { 
		super(canvas); 
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getPoint();
		tool.startShape(p);
		canvas.x = p.x;
		canvas.y = p.y; 
	}
	
}
