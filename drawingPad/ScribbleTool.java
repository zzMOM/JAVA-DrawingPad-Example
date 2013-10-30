package drawingPad;

import java.awt.Graphics;
import java.awt.Point;



public class ScribbleTool extends AbstractTool implements Tool{
	protected Stroke curStroke = null;
	
	public ScribbleTool(ScribbleCanvas canvas, String name){
		super(canvas, name);
	}
	
	/*
	 * @Override
	 * Use it every time you override a method for two benefits. 
	 * Do it so that you can take advantage of the compiler checking to make sure you actually 
	 * are overriding a method when you think you are. 
	 * This way, if you make a common mistake of misspelling a method name or not correctly matching the parameters, 
	 * you will be warned that you method does not actually override as you think it does. 
	 * Secondly, it makes your code easier to understand because it is more obvious when methods are overwritten.
	 */
	@Override
	public void startShape(Point p){
		curStroke = new Stroke(canvas.getCurColor());
		curStroke.addPoint(p);
	}
	
	@Override
	public void addPointToShape(Point p){
		if(curStroke != null){
			curStroke.addPoint(p);
			Graphics g = canvas.getGraphics();
			g.setColor(canvas.getCurColor());
			g.drawLine(canvas.x, canvas.y, p.x, p.y);
		}
	}
	
	@Override
	public void endShape(Point p){
		if(curStroke != null){
			curStroke.addPoint(p);
			canvas.addShape(curStroke);
			curStroke = null;
		}
	}
}

