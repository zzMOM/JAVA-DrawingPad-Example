package draw5;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import draw3.*;
import scribble3.ScribbleCanvas;
import scribble3.Tool;

public class DrawingPad extends draw4.DrawingPad{
	protected MultiPointsDrawingCanvas multiPointsDrawingCanvas;
	protected ActionListener multiPointsToolListener;

	public DrawingPad(String title){
		super(title);
	}
	
	//factory method
	/*protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = keyboardDrawingCanvas = new MultiPointsDrawingCanvas());
	}*/
	
	protected void initTools(){
		super.initTools();
		toolkit.addTool(new PolygonShapeTool(canvas, "Polygon", "MulitPoints"));
	}
	
	protected JComponent createToolBar(ActionListener toolListener){
		JPanel toolbar = new JPanel(new GridLayout(0, 1));
		int n = toolkit.getToolCount();
		for(int i = 0; i < n; i++){
			Tool tool = toolkit.getTool(i);
			//we create Image tool bar on the right side separately
			//disable Image tool on right tool bar
			//image button use .jpg  as icon, and all other button use .ico as icon
			if(tool != null && !tool.getTipText().equals("Image")){
				try {
					String name = tool.getName();
					name = "src/img/" + name + ".ico";
					File fileName = new File(name);
				    Image img = ImageIO.read(fileName);
				    JButton button = new JButton();
				    button.setIcon(new ImageIcon(img));
				    button.setToolTipText(tool.getName());
				    if(tool.getTipText().equals("MultiPoints")){
				    	button.addActionListener(new multiPointsToolListener());
				    } else {
				    	button.addActionListener(new toolListener());
				    }
					toolbar.add(button);
				  } catch (IOException ex) {}
			}
		}
		return toolbar;
	}
	
	class toolListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			Object source = event.getSource();
			if(source instanceof AbstractButton){
				AbstractButton button = (AbstractButton) source;
				Tool tool = toolkit.setSelectedTool(button.getToolTipText());
				keyboardDrawingCanvas.setTool(tool);
			}
		}
	};
	
	class multiPointsToolListener implements ActionListener{
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
