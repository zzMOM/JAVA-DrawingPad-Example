package drawingPad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

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
		JMenu optionMenu = menuBar.getMenu(2);
		addFontOptions(optionMenu);
	}
	
	public Tool getSelectedTool(){
		return toolkit.getSelectedTool();
	}
	
	protected void initTools(){
		toolkit = new ToolKIT();
		toolkit.addTool(new ScribbleTool(canvas, "Scribble"));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Line", new LineShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Oval", new OvalShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Rectangle", new RectangleShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Filled Oval", new FilledOvalShape()));
		toolkit.addTool(new TwoEndsShapeTool(canvas, "Filled Rectangle", new FilledRectangleShape()));
		toolkit.addTool(new TextTool(canvas, "Text"));
		drawingCanvas.setTool(toolkit.getTool(0));
	}
	
	/*
	 * Define an interface for creating an object, 
	 * but let the classes that implement the interface decide which class to instantiate. 
	 * The Factory method lets a class defer instantiation to subclasses.(non-Javadoc)
	 * 
	 */
	//factory method
	/*return ScribbleCanvas, because DrawingCanvas extends ScribbleCanvas, 
	 *and DrawingCanvas doesn't define any attributes, they are all extend from ScribbleCanvas
	 */
	@Override
	protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = new DrawingCanvas());
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
	

	
	protected void addFontOptions(JMenu optionMenu){
		String[] fontFamilyNames = {
			"Serif",
			"Sans-serif",
			"Monospaced",
			"Dialog",
			"DialogInput"
		};
		
		int[] fontSizes = {8, 10, 12, 16, 20, 24, 28, 32, 40, 48, 64};
		
		String[] fontStyleNames = {
				"plain",
				"bold",
				"italic",
				"bold italic"
		};
		
		int i;
		//font family actions
		ActionListener fontFamilyAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					String name = mi.getText();
					drawingCanvas.setFontFamily(name);
				}
			}
		};
		//add font family to menu
		JMenu fontFamilyMenu = new JMenu("Font family");
		ButtonGroup group = new ButtonGroup();
		for(i = 0; i < fontFamilyNames.length; i++){
			JCheckBoxMenuItem mi = new JCheckBoxMenuItem(fontFamilyNames[i]);
			fontFamilyMenu.add(mi);
			mi.addActionListener(fontFamilyAction);
			group.add(mi);
		}
		optionMenu.add(fontFamilyMenu);
		
		//font size actions
		ActionListener fontSizeAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					String size = mi.getText();
					try{
						drawingCanvas.setFontSize(Integer.parseInt(size));
					} catch (NumberFormatException e){}
				}
			}
		};
		//add font size to menu
		JMenu fontSizeMenu = new JMenu("Font size");
		group = new ButtonGroup();
		for(i = 0; i < fontSizes.length; i++){
			JCheckBoxMenuItem mi = new JCheckBoxMenuItem(Integer.toString(fontSizes[i]));
			fontSizeMenu.add(mi);
			mi.addActionListener(fontSizeAction);
			group.add(mi);
		}
		optionMenu.add(fontSizeMenu);
		
		//font style actions
		ActionListener fontStyleAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					String styleName = mi.getText();
					int style = Font.PLAIN;
					if(styleName.equals("bold")){
						style = Font.BOLD;
					} else if(styleName.equals("italic")){
						style = Font.ITALIC;
					} else if(styleName.equals("bold italic")){
						style = Font.BOLD | Font.ITALIC;
					}
					drawingCanvas.setFontStyle(style);
				}
			}
		};
		//add font style to menu
		JMenu fontStyleMenu = new JMenu("Font style");
		group = new ButtonGroup();
		for(i = 0; i < fontStyleNames.length; i++){
			JCheckBoxMenuItem mi = new JCheckBoxMenuItem(fontStyleNames[i]);
			fontStyleMenu.add(mi);
			mi.addActionListener(fontStyleAction);
			group.add(mi);
		}
		optionMenu.add(fontStyleMenu);
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

