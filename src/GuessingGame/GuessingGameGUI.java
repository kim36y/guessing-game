package GuessingGame;

import BinaryTree.*;
import Reader.GuessingTreeReader;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GuessingGameGUI class contains all the GUI components of the game and the functions defined for all the buttons
 * @author kim36y
 *
 */
public class GuessingGameGUI extends JPanel implements ActionListener
{
	public BinaryTree tree;
	private String question = "";
	private BinaryTreeNode node;
	private JLabel questionLabel = new JLabel(question);
	
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	
	private GuessingTreeReader read;
	
	/**
	 * constructor
	 */
	public GuessingGameGUI()
	{
		//call the super
		super();
		//convert the xml file to the tree
		read = new GuessingTreeReader("questionsAndAnswers.xml");
		tree = read.buildGameTree();
		//initialize GUI
		initGUI();
	}
	
	/**
	 * initialize the GUI for the whole game
	 */
	public void initGUI()
	{
		setLayout(new BorderLayout());
		add(instructionsPanel(), BorderLayout.WEST);
		add(gamePanel(), BorderLayout.CENTER);
	}
	
	/**
	 * instructions panel contains the list of answers in a vertical box layout
	 * @return the panel
	 */
	private JPanel instructionsPanel()
	{
		JPanel list = new JPanel();
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		
		//label at the top asking to choose a job
		JLabel choose = new JLabel("Please choose a job:");
		//set font style and size
		choose.setFont(new Font("Nanum Pen Script", Font.BOLD, 35));
		//set color of label to white
		choose.setForeground(Color.white);
		//add choose label to the instructions panel
		list.add(choose);
		
		//create an array of strings of pre-specified answers
		String[] jobs = {"Pilot", "Bus driver", "Fireman", "Car racer", "Doctor", "Lawyer", "Manager", "Waiter", "Painter", "Choreographer", "Author", "Web designer", "Construction worker", "Tour guide", "Rock climber", "Soccer player"};
		//create an array of strings of labels
		JLabel[] labels = new JLabel[jobs.length];
		
		for (int i = 0; i < jobs.length; i++)
		{
			//create new label for each string in jobs array and set same font and color for all the labels
		    labels[i] = new JLabel( jobs[i] );
		    labels[i].setForeground(Color.white);
		    labels[i].setFont(new Font("Nanum Pen Script", Font.BOLD, 25));
		    //add the label to the list
		    list.add(labels[i]);
		}
		
		//set the background color of instructions panel
		list.setBackground(new Color(39, 111, 253));
	
		return list;
	}
	
	/**
	 * panel for the game (use border layout and add mainPicture to the north and questions and buttons to the center)
	 * @return the game panel
	 */
	private JPanel gamePanel()
	{
		JPanel gamePanel = new JPanel(new BorderLayout());
		
		gamePanel.add(mainPicture(), BorderLayout.NORTH);
		gamePanel.add(questionAndAnswerPanel(), BorderLayout.CENTER);
		
		return gamePanel;
	}
	
	
	/**
	 * panel for the picture
	 * @return the panel with picture
	 */
	private JPanel mainPicture()
	{
		JPanel picturePanel = new JPanel();
		JLabel picture = new JLabel("");
		
		//set image to imageIcon (picture in the image folder)
		Image image = new ImageIcon(this.getClass().getResource("/background.png")).getImage();
		//set the label to the picture icon
		picture.setIcon(new ImageIcon(image));
		
		picturePanel.setBackground(new Color(6, 14, 90));
		picturePanel.add(picture);
		
		return picturePanel;
	}
	
	/**
	 * panel for questions in the center and buttons with answers in the south (use border layout)
	 * @return the questions
	 */
	private JPanel questionAndAnswerPanel()
	{
		JPanel questionAnswerPanel = new JPanel(new BorderLayout());
		questionAnswerPanel.add(questionPanel(), BorderLayout.CENTER);
		questionAnswerPanel.add(ButtonsPanel(), BorderLayout.SOUTH);
		
		return questionAnswerPanel;		
	}
	
	/**
	 * panel for questions (uses border layout)
	 * @return the question panel
	 */
	private JPanel questionPanel()
	{
		JPanel questionPanel = new JPanel();
		questionPanel.setLayout(new BorderLayout());
		questionPanel.add(questionLabel);
		
		//set the questionLabel to be in the center of the panel
		questionLabel.setHorizontalAlignment(JLabel.CENTER);
		questionLabel.setVerticalAlignment(JLabel.CENTER);
		
		//set font and color of questionLable 
		questionLabel.setFont(new Font("Chalkboard SE", Font.PLAIN, 27));
		questionLabel.setForeground(Color.white);
		
		//set color of the background of the panel
		questionPanel.setBackground(new Color(63, 46, 213));
		
		return questionPanel;
	}
	
	/**
	 * panel for buttons(use border layout)
	 * @return the panel for buttons
	 */
	private JPanel ButtonsPanel()
	{
		JPanel buttons = new JPanel(new BorderLayout());
		JButton start = new JButton("Start the Game!");
		//JButton start = new JButton("Start the Game!");
		start.setPreferredSize(new Dimension(200, 60));
		start.setBackground(Color.white);
		start.setContentAreaFilled(false);
		//start.setOpaque(true);
		
		start.addActionListener( new ActionListener()  {
			public void actionPerformed( ActionEvent e )
			{
				//instantiate the GuessingGameGUI class
				new GuessingGameGUI();
				//set the current node to be the root of the tree
				node = tree.getRoot();
				//set the text of the question label to be the data of the current node
				question = node.getData().toString();
				//update the text of the question label
				updateQuestion();
				//enable yes and no buttons
				yesButton.setEnabled(true);
				noButton.setEnabled(true);
			}

			
		});
		
		//add start button to the north of the buttons panel
		buttons.add(start, BorderLayout.NORTH);
		//add panel with yes and no buttons panel to the south of the buttons panel
		buttons.add(YesNoButtonsPanel(), BorderLayout.SOUTH);
		//set the color of the background of the buttons panel
		buttons.setBackground(new Color(6, 14, 90));
		
		return buttons;
		
	
	}
	
	
	/**
	 * panel for yes and no buttons (use flow layout)
	 * @return the yes/no buttons panel
	 */
	private JPanel YesNoButtonsPanel()
	{
		
		JPanel yesNoPanel = new JPanel(new FlowLayout());
		
		//set size of yes button
		yesButton.setPreferredSize(new Dimension(300, 100));
		yesButton.addActionListener( new ActionListener()  {
			public void actionPerformed( ActionEvent e )
			{
				//if the left child of current node is not empty
				if (node.getLeftChild() != null)
				{
					//go to the left child of the node
					node = node.getLeftChild();
					//if the node is leaf then add an additional string to ask the user if the node is the right answer
					if (node.isLeaf())
					{
						question = "Is it a " + node.getData().toString() + "?";
					}
					//if the node is not a leaf then print the data of the node as it is
					else
					{
						question = node.getData().toString();
					}
				}

				//if the left child of current node is empty
				else
				{
					//print out the following and disable yes and no buttons
					question = "I guessed it right! :)";
					yesButton.setEnabled(false);
					noButton.setEnabled(false);
					
				}
				//update the text of the question label every time the yes button is pressed
				updateQuestion();
			}
		});
		
		//set size of the no button to be the same as yes button
		noButton.setPreferredSize(new Dimension(300, 100));
		noButton.addActionListener( new ActionListener()  {
			public void actionPerformed( ActionEvent e )
			{
				//if the right child of the current node exists
				if (node.getRightChild() != null)
				{
					//go to the right child of the node
					node = node.getRightChild();
					//if the node is leaf then add an additional string to ask the user if the node is the right answer
					if (node.isLeaf())
					{
						question = "Is it a " + node.getData().toString() + "?";
					}
					//if the node is not a leaf then print the data of the node as it is
					else
					{
						question = node.getData().toString();
					}
				}
				
				//if the current node doesn't have a right child
				else
				{
					//set the text of the question label to be the following
					question = "Was my guess wrong? :( Let me try again!";
					//disabel yes and no buttons
					noButton.setEnabled(false);
					yesButton.setEnabled(false);
				}
				//update the text of the question label
				updateQuestion();
			}
		});
		
		//add yes and no buttons to the panel
		yesNoPanel.add(yesButton);
		yesNoPanel.add(noButton);
		//set the orientation of the buttons in the panel to be from left to right
		yesNoPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//set the background color of the panel
		yesNoPanel.setBackground(new Color(63, 46, 213));
		
		return yesNoPanel;
				
	}

	/**
	 * update the text for question label
	 */
	public void updateQuestion()
	{
		questionLabel.setText(question);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
