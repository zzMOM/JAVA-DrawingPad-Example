package scribble3;

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
