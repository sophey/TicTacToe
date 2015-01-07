package display;

import game.*;
import javax.swing.JFrame;

public class TicTacToeApplication {

	public static void main(String[] args) {
		// create a new JFrame to hold a new tictactoe instance
		JFrame ticTacToeFrame = new JFrame("Tic Tac Toe");

		// set size
		ticTacToeFrame.setSize(600, 620);

		// make a new tictactoe instance and add it
		ticTacToeFrame.add(new TicTacToeDisplay(new TicTacToeGame()));

		// exit normally on closing the window
		ticTacToeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// show frame
		ticTacToeFrame.setVisible(true);
	}

}
