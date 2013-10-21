package scribble2;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.EventListener;
import java.io.*;
import javax.swing.*;


public class ScribbleCanvas extends JPanel{
	//The list of strokes of the drawing, the elements are instance of Stroke
	protected ArrayList strokes = new ArrayList();
	
	protected Stroke curStroke = null;
	protected Color curColor = Color.black;
	
	protected EventListener listener;
	protected boolean mouseButtonDown = false;
	protected int x, y;

	public ScribbleCanvas(){
		listener = new ScribbleCanvasListener(this);
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
	
	public void setCurColor(Color curColor){
		this.curColor = curColor;
	}
	
	public Color getCurColor(){
		return curColor;
	}
	
	public void startStroke(Point p){
		curStroke = new Stroke(curColor);
		curStroke.addPoint(p);
	}
	
	public void addPointToStroke(Point p){
		if(curStroke != null){
			curStroke.addPoint(p);
		}
	}
	
	public void endStroke(Point p){
		if(curStroke != null){
			curStroke.addPoint(p);
			strokes.add(curStroke);
			curStroke = null;
		}
	}
	
	public void paint(Graphics g){
		Dimension dim = getSize();
		g.setColor(Color.white);
		g.fillRect(0, 0, dim.width, dim.height);
		g.setColor(Color.black);
		if(strokes != null){
			Iterator iter1 = strokes.iterator();
			while(iter1.hasNext()){
				Stroke stroke = (Stroke) iter1.next();
				if(stroke != null){
					g.setColor(stroke.getColor());
					Point prev = null;
					ArrayList points = stroke.getPoints();
					Iterator iter2 = points.iterator();
					while(iter2.hasNext()){
						Point cur = (Point) iter2.next();
						if(prev != null){
							g.drawLine(prev.x, prev.y, cur.x, cur.y);
						}
						prev = cur;
					}
				}
			}
		}
	}
		
	public void newFile(){
		strokes.clear();
		repaint();
	}
	
	public void openFile(String filename){
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
			strokes = (ArrayList) in.readObject();
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
			out.writeObject(strokes);
			out.close();
			System.out.println("save drawing to " + filename);
		} catch (IOException e) {
			System.out.println("Unable to open file: " + filename);
		}
		
	}
}
