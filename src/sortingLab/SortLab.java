package sortingLab;

import java.awt.Graphics;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SortLab {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1300, 600);
		
		int numRings = Integer.valueOf(JOptionPane.showInputDialog("How Many Rings?: "));
		boolean isRandom = Boolean.valueOf(JOptionPane.showInputDialog("Would You Like it to Be Randomly Assorted? Answer 'true' or 'false': "));
		
		ColorPanel towers = new ColorPanel(numRings, isRandom);
		
		frame.getContentPane().add(towers);
		frame.setVisible(true);
		
		try {
			Thread.sleep(2000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		while(true) {
			frame.repaint();
			
			selectionSort(towers);
			
			
			bubbleSort(towers);
			
			insertionSort(towers);
			
			towers.end(towers.getGraphics());
			
			break;
		}
		
	}
	
	public static void selectionSort(ColorPanel cp) { //left array
		int swapCount = 0;
		int otherCount = 0;
		for (int i = 0; i<cp.getLeft().length-1;i++) {
			int minLoc = i;
			for(int j = i+1; j<cp.getLeft().length;j++) {
				if(cp.getLeft()[j].compareTo(cp.getLeft()[minLoc]) < 0){
					minLoc = j;
					otherCount++;
				}
			}
		
			if(i!=minLoc) { //return extras
				cp.flash(cp.getLeft(),i,minLoc, cp.getGraphics());
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				cp.repaint();
				cp.swap(cp.getLeft(),i,minLoc);
				swapCount++;
			}
			cp.repaint();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		cp.printSwaps(cp.getGraphics(), swapCount, cp.getLeft());
	}
	
	public static void bubbleSort(ColorPanel cp) { //middle array
		int swapCount = 0;
		boolean hasSwaps;
		int count = 0;
		
		do {
			hasSwaps = false;
			for(int i = 0; i<cp.getMiddle().length-count-1;i++) {
				if(cp.getMiddle()[i].compareTo(cp.getMiddle()[i+1])>0) {
					cp.flash(cp.getMiddle(),i,i+1, cp.getGraphics());
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					cp.repaint();
					cp.swap(cp.getMiddle(),i,i+1);
					swapCount++;
					cp.repaint();
					try {
						Thread.sleep(1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
					hasSwaps = true;
				}
			}
			count++;
			cp.repaint();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}while(hasSwaps);
		cp.printSwaps(cp.getGraphics(), swapCount, cp.getMiddle());
	}
	
	public static void insertionSort(ColorPanel cp) { //right array
		int swapCount = 0;
		for(int i = 1; i <cp.getRight().length;i++) {
			int j = i;
			while(j > 0 && cp.getRight()[j].compareTo(cp.getRight()[j-1])<0) {
				cp.flash(cp.getRight(),j,j-1, cp.getGraphics());
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				cp.repaint();
				cp.swap(cp.getRight(),j,j-1);
				swapCount++;
				cp.repaint();
				try {
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
				j--;
			}
			cp.repaint();
			try {
				Thread.sleep(1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		cp.printSwaps(cp.getGraphics(), swapCount, cp.getRight());
	}
}