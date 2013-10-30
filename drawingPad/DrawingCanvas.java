package drawingPad;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.awt.event.*;

import javax.swing.*;

public class DrawingCanvas extends ScribbleCanvas{
	//set default font attributes
	protected String fontFamily = "Serif";
	protected int fontSize = 24; 
	protected int fontStyle = Font.PLAIN;
	protected Font font; 
	protected DrawingCanvasListener drawingCanvasListener;

	public DrawingCanvas(){
		addKeyListener((KeyListener) listener); 
		font = new Font(fontFamily, fontStyle, fontSize); 
	}
	
	// factory method 
	//***very important 
	//makeCanvaslistener() must exactly the same as the function in ScribbleCanvas
	protected EventListener makeCanvaslistener(){
		return(drawingCanvasListener = new DrawingCanvasListener(this));
	}
	
	public void setTool(Tool tool){
		drawingCanvasListener.setTool(tool);
	}
	
	public Tool getTool(){
		return drawingCanvasListener.getTool();
	}
	
	public Font getFont() { 
		return font; 
	}

	public String getFontFamily() { 
		return fontFamily;
	}

	public void setFontFamily(String fontFamily) { 
		if (fontFamily != null && !fontFamily.equals(this.fontFamily)) { 
			this.fontFamily =fontFamily;
			font = new Font(fontFamily, fontStyle, fontSize);
		}
	}

	public int getFontSize() { 
		return fontSize;
	}

	public void setFontSize(int fontSize) { 
		if (fontSize > 0 && fontSize != this.fontSize) { 
			this.fontSize = fontSize;
			font = new Font(fontFamily, fontStyle, fontSize);
		}
	}

	public int getFontStyle() {
		return fontStyle;
	}

	public void setFontStyle(int fontStyle) {
		if (fontStyle != this.fontStyle) { 
			this.fontStyle = fontStyle;
			font = new Font(fontFamily, fontStyle, fontSize);
		}
	}

	// necessary for keyboard input 
	// public boolean isFocusTraversable() { // pre 1.4
	public boolean isFocusable() { // 1.4
		return true; 
	}
}

