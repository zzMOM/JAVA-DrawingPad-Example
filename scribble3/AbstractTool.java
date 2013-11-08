package scribble3;

/*
 * getName() funciton are same for different tools.
 * extends to this abstract class, other tools classes don't need to define getName() function anymore.
 *
 */
public abstract class AbstractTool {
	protected ScribbleCanvas canvas;
	protected String name;
	protected String tipText;
	
	public String getName(){
		return name;
	}
	
	protected AbstractTool(ScribbleCanvas canvas, String name, String tipText){
		this.canvas = canvas;
		this.name = name;
		this.tipText = tipText;
	}
	
	//add function getTipText()
	public String getTipText(){
		return tipText;
	}
}
