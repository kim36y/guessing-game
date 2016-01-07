package GuessingGame;


import javax.swing.JFrame;

/**
 * Main application for starting up the GUI
 *
 */
public class GuessingGameApplication {

	
	/** 
	 * Main application to show frame for GuessingGameGUI
	 **/
	public GuessingGameApplication() 
	{
		JFrame guiFrame;
		
		// create a new JFrame to hold the game
		guiFrame = new JFrame( "Guessing Game");
		
		// set size
		guiFrame.setSize( 1250, 800);

		guiFrame.add( new GuessingGameGUI());

		// exit normally on closing the window
		guiFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	
		// show frame
		guiFrame.setVisible( true );
	}
	
	/**
	 * Start the Game!
	 */
	public static void main( String[] args )
	{
		new GuessingGameApplication();
	}
}





