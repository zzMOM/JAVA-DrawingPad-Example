package drawingPad;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/*
 * A frame, implemented as an instance of the JFrame class,
 *  is a window that has decorations such as a border, a title, and supports button components 
 *  that close or iconify the window. 
 */
public class Scribble extends JFrame{
	protected String currentFilename = null;
	protected ScribbleCanvas canvas;
	protected ActionListener exitAction;
	protected JFileChooser chooser = new JFileChooser(".");
	protected JMenuBar menuBar;
	
	public Scribble(String title){
		super(title);
		canvas = makeCanvas();
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(createMenuBar(), BorderLayout.NORTH);
		getContentPane().add(canvas, BorderLayout.CENTER);
		addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				if(exitAction != null){
					exitAction.actionPerformed(new ActionEvent(Scribble.this, 0, null));
				}
			}
		});
	}
	
	protected ScribbleCanvas makeCanvas(){
		return new  ScribbleCanvas();
	}
	
	protected JMenuBar createMenuBar(){
		menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem mi;
		
		//File menu
		menu = new JMenu("File");
		menuBar.add(menu);
		
		mi = new JMenuItem("New");
		menu.add(mi);
		mi.addActionListener(new NewFileListener());
		
		mi = new JMenuItem("Open");
		menu.add(mi);
		mi.addActionListener(new OpenFileListener());
		
		mi = new JMenuItem("Save");
		menu.add(mi);
		mi.addActionListener(new SaveFileListener());
		
		mi = new JMenuItem("Save As");
		menu.add(mi);
		mi.addActionListener(new SaveAsFileListener());
		
		menu.add(new JSeparator());
		
		exitAction = new ExitListener();
		mi = new JMenuItem("Exit");
		menu.add(mi);
		mi.addActionListener(exitAction);
		
		//option menu
		menu = new JMenu("Option");
		menuBar.add(menu);
		
		mi = new JMenuItem("Color");
		menu.add(mi);
		mi.addActionListener(new ColorListener());
		
		//horizontal space
		menuBar.add(Box.createHorizontalGlue());
		
		//Help menu
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		mi = new JMenuItem("About");
		menu.add(mi);
		mi.addActionListener(new AboutListener());
		
		return menuBar;
	}
	
	protected void newFile(){
		currentFilename = null;
		canvas.newFile();
		setTitle("Scribble Pad");
	}
	
	protected void openFile(String filename){
		currentFilename = filename;
		canvas.openFile(filename);
		setTitle("Scribble Pad [" + currentFilename + "]");
	}
	
	protected void saveFile(){
		if(currentFilename == null){
			currentFilename = "Untitled";
		}
		canvas.saveFile(currentFilename);
		setTitle("Scribble Pad [" + currentFilename + "]");
	}
	
	protected void saveFileAs(String filename){
		currentFilename = filename;
		canvas.saveFile(filename);
		setTitle("Scribble Pad [" + currentFilename + "]");
	}
	
	class NewFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			newFile();
		}
	}
	
	class OpenFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int retval = chooser.showDialog(null, "Open");
			if(retval == JFileChooser.APPROVE_OPTION){
				File theFile = chooser.getSelectedFile();
				if(theFile != null){
					if(theFile.isFile()){
						String filename = chooser.getSelectedFile().getAbsolutePath();
						openFile(filename);
					}
				}
			}
		}
	}
	
	class SaveFileListener implements ActionListener { 
	    public void actionPerformed(ActionEvent e) {
	      saveFile(); 
	    }
	  }
	
	class SaveAsFileListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int retval = chooser.showDialog(null, "Save As");
			if(retval == JFileChooser.APPROVE_OPTION);
			File theFile = chooser.getSelectedFile();
			if(theFile != null){
				if(!theFile.isDirectory()){
					String filename = chooser.getSelectedFile().getAbsolutePath();
					saveFileAs(filename);
				}
			}
		}
	}
	
	class ExitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			int result = JOptionPane.showConfirmDialog(null, "Do you want to exit Scribble Pad?",
					"Exit Scribble Pad?", JOptionPane.YES_NO_OPTION);
			if(result == JOptionPane.YES_OPTION){
				saveFile();
				System.exit(0);
			}
		}
	}
	
	class ColorListener implements ActionListener{
		protected ColorDialog dialog = new ColorDialog(Scribble.this, "Choose Color", canvas.getCurColor());
		public void actionPerformed(ActionEvent e){
			Color result = dialog.showDialog();
			if(result != null){
				canvas.setCurColor(result);
			}
		}
	}
	
	class AboutListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane.showMessageDialog(null, "DrawingPad version 1.0", "About",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}


