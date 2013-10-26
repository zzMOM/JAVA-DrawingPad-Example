package draw1;

import java.awt.*;
import java.awt.event.*;

import scribble3.*;

public class DrawingCanvasListener extends ScribbleCanvasListener{

	public DrawingCanvasListener(DrawingCanvas canvas){
		super(canvas, null);
	}
	
	public void setTool(Tool tool){
		drawingCanvasListener.setTool(tool);
	}
	
	public Tool getTool(){
		return tool;
	}
	
	pubilc void setTool(Tool tool){
		this.tool = tool;
	}
}
