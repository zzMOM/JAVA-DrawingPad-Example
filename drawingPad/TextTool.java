package drawingPad;

import java.awt.*;

public class TextTool extends AbstractTool implements KeyboardTool{
	protected StringBuffer text;
	protected Text curShape;
	
	public TextTool(ScribbleCanvas canvas, String name){
		super(canvas, name);
		text = new StringBuffer();
	}
	
	@Override
	public void startShape(Point p){
		text.delete(0, text.length());
		curShape = new Text();
		curShape.setColor(canvas.getCurColor());
		curShape.setLocation(p.x, p.y);
		if(canvas instanceof DrawingCanvas){
			curShape.setFont(((DrawingCanvas) canvas).getFont());
		}
		canvas.addShape(curShape);
	}
	
	@Override
	public void addCharToShape(char c){
		text.append(c);
		curShape.setText(text.toString());
		canvas.repaint();
	}
	
	public void addPointToShape(Point p){}
	public void endShape(Point p){}
}

