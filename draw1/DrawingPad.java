package draw1;

import java.awt.event.*;
import java.io.*;
import java.awt.*;

import javax.swing.*;

import scribble3.*;

public class DrawingPad extends Scribble{
	protected ToolKIT toolkit;
	protected DrawingCanvas drawingCanvas;
	
	public DrawingPad(String title){
		super(title);
		initTools();
		ActionListener toolListener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof AbstractButton){
					AbstractButton button = (AbstractButton) source;
					Tool tool = toolkit.setSelectedTool(button.getText());
					drawingCanvas.setTool(tool);
				}
			}
		};
		JComponent toolbar = createToolBar(toolListener);
		getContentPane().add(toolbar, BorderLayout.WEST);
		JMenu menu = createToolMenu(toolListener);
		//menuBar is the attribute from Scribble.java
		menuBar.add(menu, 1);//insert at index position 1;
	}
	
	
	public Tool getSelectedTool(){
		return toolkit.getSelectedTool();
	}
	
	protected void initTools(){
		/*
		 * add four tools to the frame
		 * and set "Scribble" as the default tool.
		 */
		toolkit = new ToolKIT();
		toolkit.addTool(new ScribbleTool(canvas, "Scribble"));
		toolkit.addTool(new TwoEndsTool(canvas, "Line", TwoEndsTool.LINE));
		toolkit.addTool(new TwoEndsTool(canvas, "Oval", TwoEndsTool.OVAL));
		toolkit.addTool(new TwoEndsTool(canvas, "Rectangle", TwoEndsTool.RECT));
		Tool temptool = toolkit.getTool(0);
		//drawingCanvas.setTool(temptool);
	}
	
	/*
	 * Define an interface for creating an object, 
	 * but let the classes that implement the interface decide which class to instantiate. 
	 * The Factory method lets a class defer instantiation to subclasses.(non-Javadoc)
	 * 
	 */
	//factory method
	protected DrawingCanvas makeCanvas(){
		return new DrawingCanvas();
	}
	
	//create tool bar button according to the number of tools.
	protected JComponent createToolBar(ActionListener toolListener){
		JPanel toolbar = new JPanel(new GridLayout(0, 1));
		int n = toolkit.getToolCount();
		for(int i = 0; i < n; i++){
			Tool tool = toolkit.getTool(i);
			if(tool != null){
				JButton button = new JButton(tool.getName());
				button.addActionListener(toolListener);
				toolbar.add(button);
			}
		}
		return toolbar;
	}
	
	protected JMenu createToolMenu(ActionListener toolListener){
		JMenu menu = new JMenu("Tools");
		int n = toolkit.getToolCount();
		for(int i = 0; i < n; i++){
			Tool tool = toolkit.getTool(i);
			if(tool != null){
				JMenuItem menuitem =  new JMenuItem(tool.getName());
				menuitem.addActionListener(toolListener);
				menu.add(menuitem);
			}
		}
		return menu;
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
