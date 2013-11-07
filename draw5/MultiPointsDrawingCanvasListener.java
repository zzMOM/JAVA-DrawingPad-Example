package draw5;

import java.awt.Point;
import java.awt.event.MouseEvent;

import draw3.KeyboardDrawingCanvas;


public class MultiPointsDrawingCanvasListener extends draw3.KeyboardDrawingCanvasListener{
	
	protected MultiPointsDrawingCanvasListener(KeyboardDrawingCanvas canvas){
		super(canvas);
	}
	
	public void mouseReleased(MouseEvent e){
		Point p = e.getPoint();
		tool.endShape(p);
		canvas.mouseButtonDown = false;
		if (tool instanceof PolygonTool) {	
			PolygonTool polygonTool = (PolygonTool) tool; 
			polygonTool.addPointToArray(p);
			if(e.getClickCount() == 2){
				polygonTool.endArray(p);
			}
		}
		
	}
	

}