package sortingLab;

import java.awt.Color;
import java.awt.Graphics;

public class Ring implements Comparable{

	private int x,y,width,height;
	Color color;
	
	Ring(int x, int y, int w){
		super();
		width = w;
		this.x =x;
		this.y = y;
		setHeight(30);
		color = new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
	}
	
	Ring(int x, int y, int w, Color c){
		this(x,y,w);
		color = c;
	}
	
	public Ring() {
		this(5,5,5);
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	public int compareTo(Object o) {
		
		return this.width - ((Ring)o).width;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(x-(width/2),y, width, height);
	}
}