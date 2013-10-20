package scribble1;

import java.awt.*;
import java.awt.event.*;
import java.util.EventListener;
import javax.swing.*;

public class ScribbleCanvas extends JPanel{
	protected EventListener listener;
	protected boolean mouseButtonDown = false;
	protected int x, y;

	public ScribbleCanvas(){
		listener = new ScribbleCanvasListener(this);
		addMouseListener((MouseListener) listener);
		addMouseMotionListener((MouseMotionListener) listener);
	}
}
