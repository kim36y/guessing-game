package UnrestrictedGame;

import BinaryTree.*;
import Reader.GuessingTreeReader;

import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

/**
 * UnrestrictedGameGUI class contains all the GUI components of the game and the functions defined for all the buttons
 * @author kim36y
 */
public class UnrestrictedGameGUI extends JPanel implements ActionListener
{
	private BinaryTree tree;
	private String question = "";
	private BinaryTreeNode node;
	private JLabel questionLabel = new JLabel(question);
	private JButton yesButton = new JButton("Yes");
	private JButton noButton = new JButton("No");
	private JButton start = new JButton("Start the Game!");
	private JPanel buttons;
	private JPanel list;
	private JButton restart;
	private String newAnswer;
	private String addQuestion;
	private String yesNoAnswer;
	private LinkedList<JLabel> newAnswersLabels;
	
	private GuessingTreeReader read;
	
	/**
	 * constructor
	 */
	public UnrestrictedGameGUI()
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
	 * set the layout to be border layout
	 * add instructions panel in the west and game panel in the center 
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
		list = new JPanel();
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
		String[] jobs = {"Pilot", "Bus driver", "Fireman", "Car racer", "Doctor", "Lawyer", "Manager", "Waiter", "Painter", "Choreographer", "Author", "Web designer", "Farmer", "Tour guide", "Rock climber", "Soccer player"};
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

		//instantiate linked list of labels for new answers
		newAnswersLabels = new LinkedList<JLabel>();
		
		//set the background color of instructions panel
		list.setBackground(new Color(39, 111, 253));
	
		return list;
	}
	
	/**
	 * panel for the game
	 * uses border layout
	 * add mainPicture to the north and questions and buttons to the center
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
	 * panel for questions (in the center) and buttons with answers(in the south)
	 * uses border layout
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
	 * panel for questions
	 * uses border layout
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
	 * panel for buttons(start button and yes/no button)
	 * uses border layout
	 * @return the panel for buttons
	 */
	private JPanel ButtonsPanel()
	{
		buttons = new JPanel(new BorderLayout());
		
		//JButton start = new JButton("Start the Game!");
		start.setPreferredSize(new Dimension(200, 60));
		start.setBackground(Color.white);
		start.setContentAreaFilled(false);
		//start.setOpaque(true);
		
		start.addActionListener( new ActionListener()  {
			public void actionPerformed( ActionEvent e )
			{
				//instantiate the UnrestrictedGameGUI class
				new UnrestrictedGameGUI();
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
	 * panel for yes and no buttons
	 * define functions for the buttons
	 * use flow layout
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
				//if the current node does not have a right child
				else
				{
					//set the label in the questions panel to display the following text
					question = "Click Restart to try again!";
					//disable yes and no buttons
					noButton.setEnabled(false);
					yesButton.setEnabled(false);
					
					//call inputAnswer to prompt the user to input new answer
					inputAnswer();
					//if the user did not type in a new answer make the window pop again
					if (newAnswer.isEmpty())
					{
						while (newAnswer.isEmpty())
						{
							inputAnswer();
						
						}
					}
					
					//call questionWindow to prompt the user to input new question
					questionWindow();
					//if the user did not type in a new question make the window pop again
					if (addQuestion.isEmpty())
					{
						while (addQuestion.isEmpty())
						{
								questionWindow();
						}
					}
					
					//call answerToQuestion to prompt the user to input an anwer of either yes or no
					answerToQuestion();
					//if the user did not type in a yes/no answer make the window pop again
					if (!yesNoAnswer.toLowerCase().equals("yes") || !yesNoAnswer.toLowerCase().equals("no"))
					{
						while (!(yesNoAnswer.toLowerCase().equals("yes")) && !(yesNoAnswer.toLowerCase().equals("no")))
						{
							answerToQuestion();
						}
						
					}
					//add new nodes to the tree
					setNewAnswer();
					//add new answers to the instructions panel
					addNewLabel();
					//get rid of start button from the panel
					start.setVisible(false);
					//add restart button to the buttons panel
					buttons.add(restartGame());
					
				}
				//update the text of the question label every time the no button is clicked
				updateQuestion();
			}
		});
		
		//add yes/no buttons to the panel
		yesNoPanel.add(yesButton);
		yesNoPanel.add(noButton);
		//set the orientation of the buttons in the panel to be from left to right
		yesNoPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//set the background color of the panel
		yesNoPanel.setBackground(new Color(63, 46, 213));
		
		return yesNoPanel;
				
	}
	
	/**
	 * button for restarting the game after the user inputs a new answer and question
	 * @return the restart button 
	 */
	private JButton restartGame()
	{
		//create a new restart button and its size to be the following dimension
		restart = new JButton("Restart");
		restart.setPreferredSize(new Dimension(200, 60));
		//define all the functions of the restart button
		restart.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e )
			{
				//get rid of the start button 
				start.setVisible(false);
				//enable the yes and no buttons for the game restarted
				noButton.setEnabled(true);
				yesButton.setEnabled(true);
				
				//reset the currentNode to the root of the tree
				node = tree.getRoot();
				//reset the question to start from the beginning(root)
				question = node.getData().toString();
				//update the question according to the changes made above
				updateQuestion();

			}
		});
		
		return restart;
	}
	
	/**
	 * add new answer and question to the tree
	 */
	public void setNewAnswer()
	{
		//store the data of the current node in a string named previousNode
		String previousNode = node.getData().toString();
		
		//store the data of the current node in tempNode
		BinaryTreeNode tempNode = new DefaultBinaryTreeNode();
		tempNode.setData(previousNode);
		
		if (addQuestion != null && !addQuestion.isEmpty())
		{
		//set the data current of the current node to the new question added
		node.setData(addQuestion);
		}
		
		//create a new node
		BinaryTreeNode newNode = new DefaultBinaryTreeNode();
		//set the data of the new node to the new answer added
		newNode.setData(newAnswer);
		
			//if the answer to the new question is yes
			if (yesNoAnswer.toLowerCase().equals("yes"))
			{
				//set the new node with new answer as left child and stored pre-specified answer as right child
				node.setLeftChild(newNode);
				node.setRightChild(tempNode);
			}
		
			//if the answer to the new question is no
			else if (yesNoAnswer.toLowerCase().equals("no"))
			{
				//set the new node with new answer as right child and stored pre-specified answer as left child
				node.setLeftChild(tempNode);
				node.setRightChild(newNode);
			}	
			
	}
	
	/**
	 * add new answer as a label to the instructions panel
	 */
	public void addNewLabel()
	{
		JLabel newAnswerLabel = new JLabel(newAnswer);
		
		newAnswersLabels.insertLast(newAnswerLabel);
		
		list.add(newAnswersLabels.getLast());
		newAnswersLabels.getLast().setForeground(Color.white);
		newAnswersLabels.getLast().setFont(new Font("Nanum Pen Script", Font.BOLD, 25));	
	}
	
	/**
	 * pop-up window for new question
	 */
	public void questionWindow() 
	{
		addQuestion = JOptionPane.showInputDialog(null, "Enter a new question for your guess", "Please Enter", JOptionPane.QUESTION_MESSAGE);
	}
	
	/**
	 * pop-up window for new answer
	 * also adds new answers to the instructions panel
	 */
	public void inputAnswer()
	{
		newAnswer = JOptionPane.showInputDialog(null, "What was your guess?", "Please Enter", JOptionPane.QUESTION_MESSAGE);	
	}
	
	/**
	 * pop up window for yes or no answer
	 */
	public void answerToQuestion()
	{
		yesNoAnswer = JOptionPane.showInputDialog(null, "Is the answer yes or no?", "Please Enter", JOptionPane.QUESTION_MESSAGE);
	}
	
	
	/**
	 * update the text for question label
	 */
	private void updateQuestion()
	{
		questionLabel.setText(question);
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
