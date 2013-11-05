package draw4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import scribble3.ScribbleCanvas;
import scribble3.Tool;




public class DrawingPad extends draw3.DrawingPad{
	protected ImageDrawingCanvas imageDrawingCanvas;
	protected ActionListener ImageButtonListener;
	
	public DrawingPad(String title){
		super(title);
		JMenu menu = menuBar.getMenu(1);
		imageOptions(menu);
		
		//create a new tool bar to show different image buttons
		JToolBar imageToolBar = new JToolBar("Images");
		addImageButtons(imageToolBar);
		//lay out the tool bar in the main panel
		getContentPane().add(imageToolBar, BorderLayout.EAST);
	}
	
	//factory method
	protected ScribbleCanvas makeCanvas(){
		return (drawingCanvas = keyboardDrawingCanvas = imageDrawingCanvas = new ImageDrawingCanvas());
	}
	
	protected void initTools(){
		super.initTools();
		toolkit.addTool(new ImageTool(canvas, "Image", "sun"));
		toolkit.addTool(new ImageTool(canvas, "Image", "moon"));
		toolkit.addTool(new ImageTool(canvas, "Image", "star"));
	}
	
	/*change button text to icon*/
	protected JComponent createToolBar(ActionListener toolListener){
		JPanel toolbar = new JPanel(new GridLayout(0, 1));
		int n = toolkit.getToolCount();
		for(int i = 0; i < n; i++){
			Tool tool = toolkit.getTool(i);
			//we create Image tool bar on the left side separately
			if(tool != null && !tool.getName().equals("Image")){
				JButton button = new JButton();
				try {
					String name = tool.getName();
					name = "src/img/" + name + ".ico";
					File fileName = new File(name);
				    Image img = ImageIO.read(fileName);
				    button.setIcon(new ImageIcon(img));
				    button.setToolTipText(tool.getName());
				  } catch (IOException ex) {}
				button.addActionListener(new toolListener());
				toolbar.add(button);
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
				imageDrawingCanvas.setTool(tool);
			}
		}
	};
	
	protected void imageOptions(JMenu optionMenu){
		String[] imageNames ={"Sun", "Moon", "Star"};
		ActionListener imageAction = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof JCheckBoxMenuItem){
					JCheckBoxMenuItem mi = (JCheckBoxMenuItem) source;
					Tool tool = toolkit.setSelectedTool(mi.getText());
					imageDrawingCanvas.setTool(tool);
				}
			}
		};
		//add image to menu
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
	
	/*
	 * create a float toolbar to show image button
	 */
	protected void addImageButtons(JToolBar imageToolBar){
		JButton button = null;
		button = makeImageButton("sun");
		imageToolBar.add(button);
		
		button = makeImageButton("moon");
		imageToolBar.add(button);
		
		button = makeImageButton("star");
		imageToolBar.add(button);
		
	}
	
	protected JButton makeImageButton(String imageName){
		//image location
		String imgLoc = "src/img/" + imageName + ".jpg";
		File imageFile = new File(imgLoc);
		
		//create and initialize the button
		JButton button = new JButton();
		button.addActionListener(new toolListener());
		button.setToolTipText(imageName);
		Image image = null;
		try {  
            // button icon Read from a file   
            image = ImageIO.read(imageFile); 
            //resize the image
            Image image2 = image.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(image2));
        } catch (IOException e) {  
            System.out.println(e.getMessage());  
        }  
		return button;
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
