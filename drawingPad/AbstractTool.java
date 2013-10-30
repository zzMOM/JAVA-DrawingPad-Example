package drawingPad;

/*
 * getName() funciton are same for different tools.
 * extends to this abstract class, other tools classes don't need to define getName() function anymore.
 *
 */
public abstract class AbstractTool {
	protected ScribbleCanvas canvas;
	protected String name;
	
	protected AbstractTool(ScribbleCanvas canvas, String name){
		this.canvas = canvas;
		this.name = name;
	}
	
	
	public String getName(){
		return name;
	}
	
}
