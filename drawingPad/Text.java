package drawingPad;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/*
 * Stroke, TwoEndsShape and Text, extends Shape class
 */
public class Text extends Shape{
	protected int x, y;
	protected String text;
	protected Font font;
	
	protected static Font defaultFont = new Font("Serif", Font.BOLD, 24);
	
	public Text(){}
	
	public Text(Color color){
		super(color);
	}
	
	public void setLocation(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}
	
	public Font getFont(){
		return font;
	}
	
	public void setFont(Font font){
		this.font = font;
	}
	
	public void draw(Graphics g){
		if(text != null){
			if(color != null){
				g.setColor(color);
			}
			if(font != null){
				g.setFont(font);
			} else {
				g.setFont(defaultFont);
			}
			g.drawString(text, x, y);
		}
	}
}

