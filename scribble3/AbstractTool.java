package scribble3;

/**
 * getName() funciton are same for different tools.
 * extends to this abstract class, other tools classes don't need to define getName() function anymore.
 * @author weiwu
 *
 */
public abstract class AbstractTool {
	protected ScribbleCanvas canvas;
	protected String name;
	
	public String getName(){
		return name;
	}
	
	protected AbstractTool(ScribbleCanvas canvas, String name){
		this.canvas = canvas;
		this.name = name;
	}
}
