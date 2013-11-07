package draw5;

import java.awt.Point;
import java.awt.event.MouseEvent;

import draw3.DrawingPad;


public class MultiPointsDrawingCanvasListener extends draw3.KeyboardDrawingCanvasListener{
	protected PolygonTool polygonTool = (PolygonTool)tool;
	
	protected MultiPointsDrawingCanvasListener(MultiPointsDrawingCanvas canvas){
		super(canvas);
	}
	
	
	public void mouseClicked(MouseEvent e){
		canvas.mouseButtonDown = false;
		Point p = e.getPoint();
		polygonTool.addPointToArray(p);
		canvas.x = p.x;
		canvas.y = p.y;
		if(e.getClickCount() == 2){
			polygonTool.endArray(p);
		}
	}
	

}