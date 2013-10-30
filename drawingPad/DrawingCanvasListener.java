package drawingPad;

import java.awt.*;
import java.awt.event.*;


public class DrawingCanvasListener extends ScribbleCanvasListener implements KeyListener{

	public DrawingCanvasListener(DrawingCanvas canvas){
		super(canvas, null);
	}
	
	public void keyPressed(KeyEvent e) {
		if (tool instanceof KeyboardTool) {	
			KeyboardTool keyboardTool = (KeyboardTool) tool; 
			keyboardTool.addCharToShape((char) e.getKeyChar()); 
		}
	} 

	public void keyReleased(KeyEvent e) {} 
	public void keyTyped(KeyEvent e) {}
  
	public void mouseClicked(MouseEvent e) {
		canvas.requestFocus(); 
	}
	
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mouseMoved(MouseEvent e){}
	
	public Tool getTool(){
		return tool;
	}
	
	public void setTool(Tool tool){
		this.tool = tool;
	}
}