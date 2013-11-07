package draw5;

import java.awt.Point;

public interface PolygonTool extends scribble3.Tool{
	public void addPointToArray(Point p);
	public void endArray(Point p);
}
