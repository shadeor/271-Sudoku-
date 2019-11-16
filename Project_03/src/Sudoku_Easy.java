import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.text.NumberFormat;

import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;

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

public class Sudoku_Easy extends JFrame implements ActionListener{


	private String[][] problem = new String[][]{//creates an array for the problem
		{"", "", "", "", "", "", "", "6", "3"},	
		{"8", "", "", "5", "", "", "1", "", "9"},
		{"", "9", "", "6", "", "4", "7", "", ""},
		{"9", "", "", "", "", "6", "", "4", ""},
		{"", "", "7", "4", "", "2", "9", "", ""},
		{"", "2", "", "1", "", "", "", "", "6"},
		{"", "", "9", "3", "", "5", "", "7", ""},
		{"1", "", "4", "", "", "8", "", "", "2"},
		{"7", "3", "", "", "", "", "", "", ""}
	};


	private String[][] keyEasy = new String[][]{//creates an array for the answer
		{"5", "7", "2", "9", "8", "1", "4", "6", "3"},	
		{"8", "4", "6", "5", "7", "3", "1", "2", "9"},
		{"3", "9", "1", "6", "2", "4", "7", "5", "8"},
		{"9", "1", "5", "8", "3", "6", "2", "4", "7"},
		{"6", "8", "7", "4", "5", "2", "9", "3", "1"},
		{"4", "2", "3", "1", "9", "7", "5", "8", "6"},
		{"2", "6", "9", "3", "1", "5", "8", "7", "4"},
		{"1", "5", "4", "7", "6", "8", "3", "9", "2"},
		{"7", "3", "8", "2", "4", "9", "6", "1", "5"}
	};

	
	//declares variables to be used below
	private JPanel panel, p;
	private JLabel title, note, solution, correct;
	private JLayeredPane lp;
	private JButton b0, b1, b2, b3;
	private int colCounter, rowCounter, x, y;
	private Graphics2D g2;
	private boolean answer = false;
	private JFormattedTextField[][] t = new JFormattedTextField[9][9];
	private JButton d = new JButton();

	private NumberFormat format = NumberFormat.getInstance();//creates a format 
	private NumberFormatter formatter = new NumberFormatter(format);//creates a formatter

	public Sudoku_Easy(){
		//creates a JFrame
		setSize(1650, 1100);
		setTitle("Sudoku: Easy");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		//creates a layered pane in order to organize the gui
		lp = getLayeredPane();
		lp.setPreferredSize(new Dimension(1000,1000));

		//adds the board to the gui
		JComponent component = new Board();

		//sets the cordinates for the board and adds it to the gui
		component.setBounds(0,0,1000,1000);
		lp.add(component, lp.DEFAULT_LAYER);

		//creates a panel and adds it to the gui
		p = new JPanel();
		p.setBounds(1000, 10, 600, 150);		
		
		//creates the label for the puzzle
		title = new JLabel("Sudoku: Easy");
		title.setForeground(Color.BLACK); //sets the color
		title.setFont(new Font("Algerian", Font.PLAIN, 65));//changes the size, and sets the font

		//creates a button that checks the Current Solution
		b0 = new JButton("Check Solution");
		b0.setFont(new Font("Algerian", Font.PLAIN, 45));
		b0.setBounds(1050, 200, 500, 100);

		//creates a button that asks for a hint
		b1 = new JButton("Request a Hint");
		b1.setFont(new Font("Algerian", Font.PLAIN, 45));
		b1.setBounds(1050, 400, 500, 100);

		//creates a button to give up
		b2 = new JButton("Give Up");
		b2.setFont(new Font("Algerian", Font.PLAIN, 45));
		b2.setBounds(1050, 600, 500, 100);

		//creates a button to go back to start screen
		b3 = new JButton("Select Another");
		b3.setFont(new Font("Algerian", Font.PLAIN, 45));
		b3.setBounds(1050, 800, 500, 100);

		//creates a label with a note to the user
		note = new JLabel("*NOTE: To change a value entered, highlight the current value, then enter a new one!");
		note.setFont(new Font("Comic Sans MS", Font.PLAIN, 35));
		note.setForeground(Color.RED);
		note.setBounds(20, 950, 1500, 50);

		//creates a label with a note to the user
		correct = new JLabel("Correct Answers Will Turn GRAY");
		correct.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		correct.setForeground(Color.GRAY);
		correct.setBounds(1050, 300, 500, 100);

		//creates a label to be shown when the solution button is pressed
		solution = new JLabel("**Solution**");
		solution.setFont(new Font("Algerian", Font.PLAIN, 150));
		solution.setBounds(20, 400, 1000, 200);
		solution.setForeground(new Color(0,0,0,50));

		//adds the components to the corresponding layers, and to the frame
		p.add(title);
		lp.add(p, lp.DEFAULT_LAYER);
		lp.add(b0, lp.DEFAULT_LAYER);
		lp.add(b1, lp.DEFAULT_LAYER);
		lp.add(b2, lp.DEFAULT_LAYER);
		lp.add(b3, lp.DEFAULT_LAYER); 
		lp.add(solution, lp.POPUP_LAYER);
		solution.setVisible(false);
		lp.add(note, lp.DEFAULT_LAYER);
		lp.add(correct, lp.DEFAULT_LAYER);
		add(new GraphicalComponent());
		
		//creates actionlisteners
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		//declares varibles to be used below
		int a = 0;
		int b = 0;
		//loops through each square adding a textbox
		for (x = 0; x < 9; x++){
			for (y = 0; y < 9; y++){
				formatter.setValueClass(Integer.class);//restricts the user to only using numbers
				formatter.setMinimum(1);//restricts the user to using a min value of 1
				formatter.setMaximum(9);//restricts the user to using a max value of 9
				formatter.setAllowsInvalid(false);//will only let the user input the prefrences above
				formatter.setCommitsOnValidEdit(false);//commits edits to the textfield

				t[x][y] = new JFormattedTextField(formatter); //creates a new textfield
				t[x][y].setOpaque(false);//makes the textboxes transparent
				Border black = BorderFactory.createLineBorder(Color.black);//makes the textboxs border's black
				t[x][y].setBorder(black);//sets them to be black
				t[x][y].setFont(new Font("Algerian", Font.PLAIN, 45));//sets font and size
				t[x][y].setHorizontalAlignment(JTextField.CENTER);//centers the values in the textboxes

				t[x][y].setBounds(a+20, b+20, 100, 100);//declares where the textboxes should be placed
				lp.add(t[x][y], lp.PALETTE_LAYER);//sets them to the jframe
				t[x][y].setVisible(true);//makes the text boxes visible
				a+= 100; //increments a by 100
			}//End of for
			b+=100;//increments b by 100
			a = 0;//resets a to 0

		}//End of for

		//loops through the textboxes
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				t[i][j].setText(problem[i][j]);//sets certain textboxes with the values for the puzzle
				t[i][j].setEnabled(false);//disables those textboxes so user may not edit
				if(t[i][j].getText().equals("")){
					t[i][j].setEnabled(true);//if there is nothing in the textboxes user may edit them
				}//End of for
			}//End of for
		}//End of for
	}//End of Sudoku_Easy

	private class GraphicalComponent extends JPanel{
		//draws the values to the board
		protected void paintComponent(Graphics g) {	
			super.paintComponent(g);
			colCounter= 0;
			rowCounter = 0;
			g.setColor(Color.BLUE); //sets the color to blue
			g2 = ( Graphics2D )g;   
			for (x = 50; x <= 900; x+=100){
				for (y = 80; y <= 900; y+=100){
					g2.setFont(new Font("Algerian", Font.PLAIN, 45));
					g2.drawString(problem[rowCounter][colCounter], x, y);//draws the values
					if(answer == true){//if user chooses to giveup
						g2.drawString(keyEasy[rowCounter][colCounter], x, y);//draws solution to the screen
					}//End of if
					rowCounter++;
				}//End of for
				colCounter++;
				rowCounter= 0;
			}//End of for
		}//End of paint component
	}//End of Graphical Component

	
	public void actionPerformed(ActionEvent e){//creates actionlistener

		//checks to see which button has been pressed
		if(e.getActionCommand().equals("Check Solution")){//if user checks the solution
			System.out.println("Checking Solution");  //debug string
			for (int i = 0; i < 9; i++){
				for (int j = 0; j < 9; j++){
					System.out.print(t[i][j].getText());  //debug string

					
					if(t[i][j].getText().equals(keyEasy[i][j])){//checks to see if each value is equal to the key				
						t[i][j].setEnabled(false);//if true the textboxes will be disabled
						t[i][j].setForeground(Color.BLUE);//the text will turn grey (not sure why)
					}//End of if
				}//End of for
			}//End of for
		}else if(e.getActionCommand().equals("Request a Hint")){//if user requests a hint
			System.out.println("I Requested a Hint"); //debug message
			for (x = 0; x < 9; x++){
				for (y = 0; y < 9; y++){
					int[] numbers = new int[9];//declares a new array
					int h = new Random().nextInt(numbers.length);//creates a value with a random number
					int o = new Random().nextInt(numbers.length);//creates a value with a random number
					t[h][o].setText(keyEasy[h][o]);//sets a few random textbox to the correct value
					t[h][o].setForeground(Color.darkGray);//sets the texts color to grey
					t[h][o].setEnabled(false);//disables those text boxes
					x++;
					y++;
				}//End of for
			}//End of for
		}else if(e.getActionCommand().equals("Give Up")){//if user gives up
			System.out.println("I Gave Up");//debug message
			int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to GIVE UP?");//double checks to see if user wants to give up
			switch (result) {
			case 0://if user chooses yes
				answer = true;//reveals answer
				//loops through the board
				for (x = 0; x < 9; x++){
					for (y = 0; y < 9; y++){
						t[x][y].setVisible(false);//hides the textboxes
						t[x][y].setEditable(false);//will not let user put in input
						solution.setVisible(true);//sets the solution so it can be seen
						b0.removeActionListener(this);//removes the actionlistener to check the solution
						b1.removeActionListener(this);//removes the actionlister to request a hint
						b2.removeActionListener(this);//removes the actionlister to giveup
					}//End of for
				}//End of for
				break;
			case 1: //if user chooses no
				//this does nothing
				break;
			}//End of Switch
			repaint();//draws the answers to the screen
		}else{
			Sudoku s = new Sudoku();//returns to the main screen
			s.main(null);
			dispose();//disposes the current jframe
			System.out.println("Returning to Start Screen"); //Debug message
		}//End of if/else
	}//End of actionPerformed


	public static void main (String[] args){
		//makes a new object for the class to be run
		Sudoku_Easy sE = new Sudoku_Easy();
		sE.setVisible(true);
		sE.setResizable(false);
	}//End of main


}//End of class
