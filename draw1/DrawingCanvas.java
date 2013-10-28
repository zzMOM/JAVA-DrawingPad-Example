package draw1;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;
import javax.swing.*;
import scribble3.*;

public class DrawingCanvas extends ScribbleCanvas{
	protected DrawingCanvasListener drawingCanvasListener;

	public DrawingCanvas(){}
	
	// factory method 
	protected EventListener makeCanvaslistener(){
		return(drawingCanvasListener = new DrawingCanvasListener(this));
	}
	
	public void setTool(Tool tool){
		drawingCanvasListener.setTool(tool);
	}
	
	public Tool getTool(){
		return drawingCanvasListener.getTool();
	}
}
