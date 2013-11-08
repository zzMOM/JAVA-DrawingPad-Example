package draw5;

import java.util.EventListener;

import draw1.*;
import draw3.KeyboardDrawingCanvasListener;

public class MultiPointsDrawingCanvas extends draw3.KeyboardDrawingCanvas{
	public MultiPointsDrawingCanvas() {}

	// factory method 
	protected EventListener makeCanvaslistener() {
		return (drawingCanvasListener = new MultiPointsDrawingCanvasListener(this)); 
	}
	
}
