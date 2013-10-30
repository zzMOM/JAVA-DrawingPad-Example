package drawingPad;

import java.awt.Point;
/*
 * an interface is a group of related methods with empty bodies.
 * Implementing an interface allows a class to become more formal about the behavior it promises to provide. 
 * Interfaces form a contract between the class and the outside world, and this contract is enforced at build time by the compiler.
 * If your class claims to implement an interface, 
 * all methods defined by that interface must appear in its source code before the class will successfully compile.
 *
 */
public interface Tool {
	public String getName();
	//Point p is from ScribbleCanvasListener-mousePressed in an instance class.
	public void startShape(Point p);
	public void addPointToShape(Point p);
	public void endShape(Point p);
}
