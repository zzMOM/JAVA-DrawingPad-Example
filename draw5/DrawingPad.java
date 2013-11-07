package draw5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

import scribble3.ScribbleCanvas;
import scribble3.Tool;

public class DrawingPad extends draw4.DrawingPad{
	protected MultiPointsDrawingCanvas multiPointsDrawingCanvas;

	public DrawingPad(String title){
		super(title);
	}
	
	//factory method
	protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = keyboardDrawingCanvas = new MultiPointsDrawingCanvas());
	}
	
	protected void initTools(){
		super.initTools();
		toolkit.addTool(new PolygonShapeTool(canvas, "Polygon"));
	}
	
	class toolListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			if(source instanceof AbstractButton){
				AbstractButton button = (AbstractButton) source;
				Tool tool = toolkit.setSelectedTool(button.getToolTipText());
				multiPointsDrawingCanvas.setTool(tool);
			}
		}
	};
	
	public static void main(String[] args) {
		int width = 1200;
		int height = 600;
	    JFrame frame = new DrawingPad("Drawing Pad");
	    frame.setSize(width, height);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(screenSize.width/2 - width/2,
			      screenSize.height/2 - height/2);
	    frame.show();
	}
}
