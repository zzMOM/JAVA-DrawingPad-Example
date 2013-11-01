package draw4;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

import scribble3.ScribbleCanvas;
import draw3.KeyboardDrawingCanvas;
import draw3.TextTool;

public class DrawingPad extends draw3.DrawingPad{
	protected ImageDrawingCanvas imageDrawingCanvas;
	
	public DrawingPad(String title){
		super(title);
		JMenu menu = menuBar.getMenu(2);
		imageOptions(menu);
	}
	
	//factory method
	protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = imageDrawingCanvas = new ImageDrawingCanvas());
	}
	
	protected void initTools(){
		super.initTools();
	}
	
	protected void imageOptions(JMenu optionMenu){
		String[] imageNames ={"Sun", "Moon", "Star"};
		ActionListener imageAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					String name = mi.getText();
					URL imageSrc = null;
					try {
			            imageSrc = new URL(name);
			        } catch (MalformedURLException e) {}
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
}
