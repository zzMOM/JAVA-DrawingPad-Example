package draw1;

import java.awt.event.ActionEvent;

import javax.swing.AbstractButton;

public class DrawingPad extends Scribble{
	
	public DrawingPad(String title){
		super(title);
		initTools();
		ActionListener toolListener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				Object source = event.getSource();
				if(source instanceof AbstractButton){
					AbstractButton button = (AbstractButton) source;
					Tool tool = toolket.setSelectedTool(button.getText());
					drawingCanvas.setTool(tool);
				}
			}
		};
	}
}
