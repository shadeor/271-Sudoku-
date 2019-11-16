
import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

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

public class Sudoku extends JFrame implements ActionListener{

	//declares varibles to be used below
	JLabel name;
	JButton easy,medium,hard;
	JLabel n1,n2,n3,n4,n5,n6,n7,n8,n9,n01,n02,n03,n04;
	
	public Sudoku() {

		super("SUDOKU");//jframe title

		JPanel panel = new JPanel();
		JLayeredPane lp = new JLayeredPane();
		lp.setPreferredSize(new Dimension( 1000,3000));
		panel.add(lp);
		
		//set button name 
		easy = new JButton ("Easy");
		medium = new JButton ("Medium");
		hard = new JButton("Hard");
		name = new JLabel("SUDOKU");
		
		//creates number decoration
		n1 = new JLabel("1");
		n2 = new JLabel("2");
		n3 = new JLabel("3");
		n4 = new JLabel("4");
		n5 = new JLabel("5");
		n6 = new JLabel("6");
		n7 = new JLabel("7");
		n8 = new JLabel("8");
		n9 = new JLabel("9");
		n01 = new JLabel("1");
		n02 = new JLabel("2");
		n03 = new JLabel("3");
		n04 = new JLabel("4");
		
		
		//set buttons font
		easy.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
		medium.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
		hard.setFont(new Font("Comic Sans MS", Font.BOLD, 80));
		
		//set label's font
		name.setFont(new Font("Ravie",Font.BOLD,180));
	
		//changes the decoration's font
		n1.setFont(new Font("Jokerman",Font.PLAIN,80));
		n2.setFont(new Font("Brush Script MT",Font.PLAIN,100));
		n3.setFont(new Font("Chiller",Font.PLAIN,160));
		n4.setFont(new Font("Colonna MT",Font.PLAIN,200));
		n5.setFont(new Font("Gigi",Font.PLAIN,150));
		n6.setFont(new Font("Jokerman",Font.PLAIN,150));
		n7.setFont(new Font("Bradley Hand ITC",Font.PLAIN,160));
		n8.setFont(new Font("Mistral",Font.PLAIN,120));
		n9.setFont(new Font("Parchment",Font.PLAIN,170));
		n01.setFont(new Font("Parchment",Font.PLAIN,200));
		n02.setFont(new Font("Gigi",Font.PLAIN,80));
		n03.setFont(new Font("Mistral",Font.PLAIN,120));
		n04.setFont(new Font("Chiller",Font.PLAIN,140));
		
		//set the background color of the button and panel
		easy.setBackground( new Color(225,218,123) );
		medium.setBackground( new Color(225,218,123) );
		hard.setBackground( new Color(225,218,123) );
		panel.setBackground( new Color(231,187,84) );
		
		//add to panel
		add(panel);
		
		//puts the panels, labels, and buttons in the correct areas on the jframe
		//setBounds(x, y, width, height)
		name.setBounds(10, 110, 1800, 200);
		lp.add(name, lp.DEFAULT_LAYER);
		
		easy.setBounds(290, 400, 400, 120);
		lp.add(easy, lp.DEFAULT_LAYER);
		
		medium.setBounds(290, 580, 400, 120);
		lp.add(medium, lp.DEFAULT_LAYER);
		
		hard.setBounds(290, 760, 400, 120);
		lp.add(hard, lp.DEFAULT_LAYER);
		
		n1.setBounds(0, 500, 300, 300);
		lp.add(n1, lp.DEFAULT_LAYER);
		n2.setBounds(20, 300, 300, 300);
		lp.add(n2, lp.DEFAULT_LAYER);
		n3.setBounds(70, 400, 300, 300);
		lp.add(n3, lp.DEFAULT_LAYER);
		n4.setBounds(120, 580, 300, 300);
		lp.add(n4, lp.DEFAULT_LAYER);
		n5.setBounds(50, 750, 300, 300);
		lp.add(n5, lp.DEFAULT_LAYER);
		n6.setBounds(700, 750, 300, 300);
		lp.add(n6, lp.DEFAULT_LAYER);
		n7.setBounds(750, 300, 300, 300);
		lp.add(n7, lp.DEFAULT_LAYER);
		n8.setBounds(800, 530, 300, 300);
		lp.add(n8, lp.DEFAULT_LAYER);
		n9.setBounds(200, 400, 300, 300);
		lp.add(n9, lp.DEFAULT_LAYER);
		n01.setBounds(960, 650, 300, 300);
		lp.add(n01, lp.DEFAULT_LAYER);
		n02.setBounds(850, 750, 300, 300);
		lp.add(n02, lp.DEFAULT_LAYER);
		n03.setBounds(930, 250, 300, 300);
		lp.add(n03, lp.DEFAULT_LAYER);
		n04.setBounds(900, 450, 300, 300);
		lp.add(n04, lp.DEFAULT_LAYER);
		
		//adds the actionlisters to the buttons
		easy.addActionListener(this);
		medium.addActionListener(this);
		hard.addActionListener(this);
	}//end of sudoku
	
	public void actionPerformed(ActionEvent e){

		//checks to see which button has been pressed
		if(e.getActionCommand().equals("Easy")){
			Sudoku_Easy sE = new Sudoku_Easy();
			sE.main(null);
			dispose();
		}else if(e.getActionCommand().equals("Medium")){
			Sudoku_Medium sM = new Sudoku_Medium();
			sM.main(null);
			dispose();
		}else{
			Sudoku_Hard sH = new Sudoku_Hard();
			sH.main(null);
			dispose();
		}//End of if/else
	}//End of actionevent
	
	//make object visible
	public static void main(String[] args) {
		Sudoku s =  new Sudoku();
		s.setSize(1650,1100);
		s.setDefaultCloseOperation(EXIT_ON_CLOSE);
		s.setVisible(true);

	}//End of main

}//End of class