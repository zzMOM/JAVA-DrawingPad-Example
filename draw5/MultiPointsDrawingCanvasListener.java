package draw5;

import java.awt.Point;
import java.awt.event.MouseEvent;

import draw3.KeyboardDrawingCanvas;


public class MultiPointsDrawingCanvasListener extends draw3.KeyboardDrawingCanvasListener{
	protected PolygonTool polygonTool = (PolygonTool) tool; 
	
	protected MultiPointsDrawingCanvasListener(KeyboardDrawingCanvas canvas){
		super(canvas);
	}
	
	
	public void mousePressed(MouseEvent e){
		Point p = e.getPoint();
		if(!(tool instanceof PolygonTool)){
			tool.startShape(p);
			canvas.mouseButtonDown = true;
		}
		canvas.x = p.x;
		canvas.y = p.y;
	}
	
	public void mouseReleased(MouseEvent e){
		Point p = e.getPoint();
		if(tool instanceof PolygonTool){
			polygonTool.addPointToArray(p);
			if(e.getClickCount() == 2){
				polygonTool.endArray(p);
			}
		} else {
			tool.endShape(p);
			canvas.mouseButtonDown = false;
		}
	}
	
	public void mouseDragged(MouseEvent e){
		Point p = e.getPoint();
		if(!(tool instanceof PolygonTool)){
			if(canvas.mouseButtonDown){
				tool.addPointToShape(p);
				canvas.x = p.x;
				canvas.y = p.y;
			}
		}
	}
	

}