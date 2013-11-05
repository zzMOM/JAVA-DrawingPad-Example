package draw4;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;

import scribble3.ScribbleCanvas;
import scribble3.Tool;




public class DrawingPad extends draw3.DrawingPad{
	//protected ImageDrawingCanvas imageDrawingCanvas;
	
	public DrawingPad(String title){
		super(title);
		
		//create a new tool bar to show different image buttons
		JToolBar imageToolBar = new JToolBar("Images", JToolBar.VERTICAL);
		addImageButtons(imageToolBar);
		//lay out the tool bar in the main panel
		getContentPane().add(imageToolBar, BorderLayout.EAST);
	}
	
	//factory method
	//protected ScribbleCanvas makeCanvas(){
	//	return (drawingCanvas = keyboardDrawingCanvas = imageDrawingCanvas = new ImageDrawingCanvas());
	//}
	
	protected void initTools(){
		super.initTools();
		//insert three imageTool into toolkit
		//imageTool will show separately, disable imageTool show in left tool bar later
		toolkit.addTool(new ImageTool(canvas, "sun"));
		toolkit.addTool(new ImageTool(canvas, "moon"));
		toolkit.addTool(new ImageTool(canvas, "star"));
	}
	
	/*change button text to icon*/
	protected JComponent createToolBar(ActionListener toolListener){
		JPanel toolbar = new JPanel(new GridLayout(0, 1));
		int n = toolkit.getToolCount();
		for(int i = 0; i < n; i++){
			Tool tool = toolkit.getTool(i);
			//we create Image tool bar on the right side separately
			//disable Image tool on left tool bar
			//image button use .jpg  as icon, and all other button use .ico as icon
			if(tool != null ){
				try {
					String name = tool.getName();
					name = "src/img/" + name + ".ico";
					File fileName = new File(name);
				    Image img = ImageIO.read(fileName);
				    JButton button = new JButton();
				    button.setIcon(new ImageIcon(img));
				    button.setToolTipText(tool.getName());
				    button.addActionListener(new toolListener());
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
