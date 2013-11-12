package draw6;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import scribble3.ScribbleTool;
import draw1.LineShape;
import draw1.OvalShape;
import draw1.RectangleShape;
import draw1.ToolKIT;
import draw2.FilledOvalShape;
import draw2.FilledRectangleShape;
import draw2.TwoEndsShapeTool;
import draw3.TextTool;
import draw4.ImageTool;
import draw5.*;

public class DrawingPad extends draw5.DrawingPad{
	public DrawingPad(String title){
		super(title);
	}
	
	protected void initTools(){
		toolkit = new ToolKIT();
		toolkit.addTool(new ScribbleTool(canvas, "Scribble", "Scribble"));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Line", "TwoEnds", new LineShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Oval", "TwoEnds", new OvalShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Rectangle", "TwoEnds", new RectangleShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Filled Oval", "TwoEnds", new FilledOvalShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Filled Rectangle", "TwoEnds", new FilledRectangleShape()));
		toolkit.addTool(new TextTool(canvas, "Text", "Text"));
		toolkit.addTool(new ImageTool(canvas, "sun", "Image"));
		toolkit.addTool(new ImageTool(canvas, "moon", "Image"));
		toolkit.addTool(new ImageTool(canvas, "star", "Image"));
		toolkit.addTool(new MultiPointsShapeTool(canvas, "Polygon", "MulitPoints", new PolygonShape()));
		toolkit.addTool(new MultiPointsShapeTool(canvas, "Filled Polygon", "MulitPoints", new FilledPolygonShape()));
		toolkit.addTool(new MultiPointsShapeTool(canvas, "Polyline", "MulitPoints", new PolylineShape()));
		drawingCanvas.setTool(toolkit.getTool(0));
	}
	
	public static void main(String[] args) {
		int width = 800;
		int height = 400;
	    JFrame frame = new DrawingPad("Drawing Pad");
	    frame.setSize(width, height);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(screenSize.width/2 - width/2,
			      screenSize.height/2 - height/2);
	    frame.show();
	}
}
