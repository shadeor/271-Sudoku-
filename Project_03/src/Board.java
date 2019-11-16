import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * Project 3 for CSE 271, that uses 2d arrays, actionListener, graphics, guis and 
 * components to create sudoku.
 * 
 * @author Olivia Shade and Yushu Gao
 * @version 2017.11.12
 *
 * Professor: Dr. Gani
 * Course: CSE 271
 * 
 * This code was mostly written on our own, though we did have some help from the old TA, Travis.
 * 
 */

public class Board extends JComponent{

	public void paintComponent(Graphics g){
		fillSquares(g, 100, 100);//calls the fill squares method
		drawOutline(g, 300, 300);//calls the drawoutline method
		validate();//validates the code so it can be seen
	}//End of paint Component


	public void drawOutline(Graphics g, int x, int y){
		for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				Graphics2D g2 = (Graphics2D)g;
				g.setColor(Color.BLACK);//sets the color to black
				g2.setStroke(new BasicStroke(5));//sets the stroke size
				g2.drawRect(col*300+20, row*300+20, 300, 300);//draws the thicker lines 
				//g2.drawRect(x, y, width, height);
			}//End of col 
		}//End of row
	}//End of drawOutline


	public void fillSquares(Graphics g, int x, int y){
		for(int row = 0; row < 9; row++){
			for(int col = 0; col < 9; col++){
				g.drawRect(col*100+20, row*100+20, 100, 100);//draws the individual boxes
			}//End of col
		}//End of row
	}//End of fillSquares 

}//End of class
