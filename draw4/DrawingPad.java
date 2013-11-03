package draw4;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

import scribble3.ScribbleCanvas;




public class DrawingPad extends draw3.DrawingPad{
	protected ImageDrawingCanvas imageDrawingCanvas;
	
	public DrawingPad(String title){
		super(title);
		JMenu menu = menuBar.getMenu(1);
		imageOptions(menu);
	}
	
	//factory method
	protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = imageDrawingCanvas = new ImageDrawingCanvas());
	}
	
	protected void initTools(){
		super.initTools();
		toolkit.addTool(new ImageTool(canvas, "Image"));
	}
	
	protected void imageOptions(JMenu optionMenu){
		String[] imageNames ={"Sun", "Moon", "Star"};
		ActionListener imageAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					String name = mi.getText();
					name = "draw4/" + name.toLowerCase() + ".jpg";
					File imageSrc = null;
			        //try {
						imageSrc = new File(name);
					//} catch (MalformedURLException e) {
					//	e.printStackTrace();
					//}
					imageDrawingCanvas.setURL(imageSrc);
				}
			}
		};
		//add font family to menu
		JMenu imageMenu = new JMenu("Image");
		ButtonGroup group = new ButtonGroup();
		for(int i = 0; i < imageNames.length; i++){
			JCheckBoxMenuItem mi = new JCheckBoxMenuItem(imageNames[i]);
			imageMenu.add(mi);
			mi.addActionListener(imageAction);
			group.add(mi);
		}
		optionMenu.add(imageMenu);
	}
	
	public static void main(String[] args) {
		int width = 600;
		int height = 400;
	    JFrame frame = new DrawingPad("Drawing Pad");
	    frame.setSize(width, height);
	    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation(screenSize.width/2 - width/2,
			      screenSize.height/2 - height/2);
	    frame.show();
	}
}
