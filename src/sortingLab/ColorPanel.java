package sortingLab;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ColorPanel extends JPanel {
	private Ring[] left;
	private Ring[] middle;
	private Ring[] right;
	
	public ColorPanel(int count, boolean random) { //boolean is for random or reverse order
		left = new Ring[count];
		middle = new Ring[count];
		right = new Ring[count];
		
		//randomize it!!!
		ArrayList<Integer> temp = new ArrayList<Integer>();
		int num;
		int amt = 0;
		while(amt<count) {
			for(int i = 0; i<count; i++) {
				num = (int)((Math.random()*count)+1);
				if(!temp.contains(num)) {
					temp.add(num);
					amt++;
				}
			}
		}
		if(random) { //random widths
			for(int i = 0; i<count;i++) {
				left[i] = new Ring(200,30*i+60,30*temp.get(i)+10);
				middle[i] = new Ring(600,30*i+60, 30*temp.get(i)+10, left[i].getColor()); //x changes per tower
				right[i] = new Ring(1000,30*i+60, 30*temp.get(i)+10, left[i].getColor());
			}
		}else { //reverse order here
			for(int i = 0; i<count;i++) {
				left[i] = new Ring(200, 30*i+60, (count-i)*30+10);
				middle[i] = new Ring(600, 30*i+60, (count-i)*30+10,left[i].getColor());
				right[i] = new Ring(1000, 30*i+60, (count-i)*30+10, left[i].getColor());
			}
		}
		
		setBackground(Color.LIGHT_GRAY);
		setSize(1300,600);
	}
	
	public ColorPanel() { //use previous
		this(5,true);
	}
	
	public Ring[] getLeft() {
		return left;
	}

	public void setLeft(Ring[] left) {
		this.left = left;
	}

	public Ring[] getMiddle() {
		return middle;
	}

	public void setMiddle(Ring[] middle) {
		this.middle = middle;
	}

	public Ring[] getRight() {
		return right;
	}

	public void setRight(Ring[] right) {
		this.right = right;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i = 0; i<left.length;i++) {
			left[i].draw(g);
		}
		for(int i = 0; i<middle.length;i++) {
			middle[i].draw(g);
		}
		for(int i = 0; i<right.length;i++) {
			right[i].draw(g);
		}
		g.setColor(Color.BLACK);
		g.getFont();
		g.drawString("SELECTION SORT",150,400); //mess with these
		g.drawString("BUBBLE SORT",560, 400);
		g.drawString("INSERTION SORT",950,400);
		
	}
	
	public void swap(Ring[] arr, int one, int two) {
		int temp1 = arr[one].getY();
		arr[one].setY(arr[two].getY());
		arr[two].setY(temp1);
		
		Ring temp2 = arr[one];
		arr[one] = arr[two];
		arr[two] = temp2;
	}
	
	public void flash(Ring[] arr, int one, int two, Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		
		int[] leftX = new int[3];
		int[] yValues = new int[3];
		int[] rightX = new int[3];
		int[] leftX2 = new int[3];
		int[] yValues2 = new int[3];
		int[] rightX2 = new int[3];
		
		leftX[0] = arr[one].getX()-(arr[one].getWidth()/2)-20;
		leftX[1] = arr[one].getX()-(arr[one].getWidth()/2)-10;
		leftX[2] = arr[one].getX()-(arr[one].getWidth()/2)-20;
		
		yValues[0] = arr[one].getY();
		yValues[1] = arr[one].getY()+15;
		yValues[2] = arr[one].getY()+30;
		
		rightX[0] = arr[one].getX()+(arr[one].getWidth()/2)+20;
		rightX[1] = arr[one].getX()+(arr[one].getWidth()/2)+10;
		rightX[2] = arr[one].getX()+(arr[one].getWidth()/2)+20;
		
		leftX2[0] = arr[two].getX()-(arr[two].getWidth()/2)-20;
		leftX2[1] = arr[two].getX()-(arr[two].getWidth()/2)-10;
		leftX2[2] = arr[two].getX()-(arr[two].getWidth()/2)-20;
		
		yValues2[0] = arr[two].getY();
		yValues2[1] = arr[two].getY()+15;
		yValues2[2] = arr[two].getY()+30;
		
		rightX2[0] = arr[two].getX()+(arr[two].getWidth()/2)+20;
		rightX2[1] = arr[two].getX()+(arr[two].getWidth()/2)+10;
		rightX2[2] = arr[two].getX()+(arr[two].getWidth()/2)+20;
		
		paintComponent(g);
		g.fillPolygon(leftX, yValues, 3);
		g.fillRect(arr[one].getX()-(arr[one].getWidth()/2)-30, arr[one].getY()+8, 10, 14);
		
		g.fillPolygon(rightX, yValues, 3);
		g.fillRect(arr[one].getX()+(arr[one].getWidth()/2)+20, arr[one].getY()+8, 10, 14);
		
		g.fillPolygon(leftX2, yValues2, 3);
		g.fillRect(arr[two].getX()-(arr[two].getWidth()/2)-30, arr[two].getY()+8, 10, 14);
		
		g.fillPolygon(rightX2, yValues2, 3);
		g.fillRect(arr[two].getX()+(arr[two].getWidth()/2)+20, arr[two].getY()+8, 10, 14);
	}

	public void end(Graphics g) {
		paintComponent(g);
		g.setColor(getBackground());
		g.drawRect(0, 0, 1300, 600);
		g.setColor(Color.red);
		g.drawString("All Sorted!", 550, 500);
		try {
			Thread.sleep(1000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void printSwaps(Graphics g,int swaps, Ring arr[]) {
		paintComponent(g);
		g.setColor(getBackground());
		g.drawRect(0, 0, 1300, 600);
		g.setColor(Color.MAGENTA);
		g.drawString("Number of Swaps: "+swaps, arr[0].getX()-arr[0].getWidth()/2 -30, 500);
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
}