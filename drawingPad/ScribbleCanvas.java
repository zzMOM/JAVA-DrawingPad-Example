package drawingPad;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.EventListener;
import java.io.*;
import javax.swing.*;

/*
 * The JPanel class provides general-purpose containers for lightweight components. 
 * By default, panels do not add colors to anything except their own background; 
 * however, you can easily add borders to them and otherwise customize their painting.
 */
public class ScribbleCanvas extends JPanel{
	//The list of strokes of the drawing, the elements are instance of Stroke
	protected ArrayList shapes = new ArrayList();
	protected Color curColor = Color.black;
	protected EventListener listener;
	public boolean mouseButtonDown = false;
	public int x, y;

	public ScribbleCanvas(){
		//calling factory method
		listener = makeCanvaslistener();
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	protected EventListener makeCanvaslistener(){
		return new ScribbleCanvasListener(this);
	}
	
	public void setCurColor(Color curColor){
		this.curColor = curColor;
	}
	
	public Color getCurColor(){
		return curColor;
	}
	
	public void addShape(Shape shape){
		if(shape != null){
			shapes.add(shape);
		}
	}
	
	
	public void paint(Graphics g){
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.black);
		if(shapes != null){
			Iterator iter = shapes.iterator();
			while(iter.hasNext()){
				Shape shape = (Shape) iter.next();
				if(shape != null){
					shape.draw(g);
				}
			}
		}
	}
		
	public void newFile(){
		shapes.clear();
		repaint();
	}
	
	/*
	 * Classes ObjectInputStream and ObjectOutputStream are high-level streams
	 *  that contain the methods for serializing and deserializing an object.
	 */
	public void openFile(String filename){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			shapes = (ArrayList) in.readObject();
			in.close();
			repaint();
		} catch (IOException e){
			System.out.println("Unable to open file: " + filename);
		} catch (ClassNotFoundException e2){
			System.out.print(e2);
		}
	}
	
	public void saveFile(String filename){
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(shapes);
			out.close();
			System.out.println("save drawing to " + filename);
		} catch (IOException e) {
			System.out.println("Unable to open file: " + filename);
		}
		
	}
}
