package display;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.*;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 * Displays the Tic Tac Toe board and uses a MouseListener to click on the
 * squares
 * 
 * @author Sophey
 *
 */
public class TicTacToeDisplay extends JComponent implements MouseListener {

    TicTacToeGame game;
    int blockSize = 200; // default blockSize

    /**
     * Constructor for the display, instantiates the game and sets the
     * background to white, also adds the mouselistener
     * 
     * @param game
     *            game
     */
    public TicTacToeDisplay(TicTacToeGame game) {
	this.game = game;
	this.setOpaque(true);
	this.setBackground(Color.white);
	addMouseListener(this);
    }

    /**
     * Overrides default paint method to paint the grid and the tictacs
     */
    public void paint(Graphics g) {
	paintGrid(g);
	paintTicTacs(g);
    }

    /**
     * Paints the grid
     * 
     * @param g
     */
    private void paintGrid(Graphics g) {
	// width and height of lines calculated using the blockSize
	int width = blockSize * 3;
	int height = blockSize * 3;

	// starts out at 0 values for row and column
	int rowIncrement = 0;
	int colIncrement = 0;

	g.setColor(Color.BLACK);

	// draws the grid
	for (int i = 0; i < 2; i++) {
	    rowIncrement += blockSize;
	    g.drawLine(0, rowIncrement, width, rowIncrement);
	}
	for (int j = 0; j < 2; j++) {
	    colIncrement += blockSize;
	    g.drawLine(colIncrement, 0, colIncrement, height);
	}
    }

    /**
     * Paints the tictacs
     * 
     * @param g
     */
    private void paintTicTacs(Graphics g) {
	int[] board = game.getBoard();
	// if the value at i is a 1 or 2, paint x or o correspondingly
	for (int i = 0; i < board.length; i++) {
	    int ticTac = board[i];
	    if (ticTac == 1)
		paintO(i, g);
	    else if (ticTac == 2)
		paintX(i, g);
	}
    }

    /**
     * Paints an O at the box number numBox
     * 
     * @param numBox
     *            box number
     * @param g
     */
    private void paintO(int numBox, Graphics g) {
	int row = numBox / 3;
	int col = numBox % 3;
	// starting x and y values
	int x = (int) ((col + .1) * blockSize);
	int y = (int) ((row + .1) * blockSize);

	// paints a big circle on the outside, fills an inner circle white
	g.setColor(Color.blue);
	g.fillOval(x, y, blockSize * 4 / 5, blockSize * 4 / 5);
	g.setColor(Color.white);
	g.fillOval((int) ((col + .3) * blockSize),
	        (int) ((row + .3) * blockSize), blockSize * 2 / 5,
	        blockSize * 2 / 5);
    }

    /**
     * Paints an X at the box number numBox
     * 
     * @param numBox
     *            box number
     * @param g
     */
    private void paintX(int numBox, Graphics g) {
	int row = numBox / 3;
	int col = numBox % 3;
	// starting x and y values
	int x = (int) ((col + .1) * blockSize);
	int y = (int) ((row + .1) * blockSize);
	// x and y values for the rectangles
	int[] xPoints = { x, (int) (x + .15 * blockSize),
	        (int) (x + .8 * blockSize), (int) (x + .65 * blockSize) };
	int[] yPoints = { (int) (y + .15 * blockSize), y,
	        (int) (y + .65 * blockSize), (int) (y + .8 * blockSize) };
	int[] xPoints2 = { x, (int) (x + .15 * blockSize),
	        (int) (x + .8 * blockSize), (int) (x + .65 * blockSize) };
	int[] yPoints2 = { (int) (y + .65 * blockSize),
	        (int) (y + .8 * blockSize), (int) (y + .15 * blockSize), y };
	g.setColor(Color.red);
	// paints 2 rectangles to make an x
	g.fillPolygon(xPoints, yPoints, 4);
	g.fillPolygon(xPoints2, yPoints2, 4);
    }

    /**
     * A congratulations box pops up with an option to replay
     * 
     * @param winner
     *            1 for O winner, 2 for X winner
     */
    public void congratsBox(int winner) {
	// default winner x, if not, make winner o
	String win = "X";
	if (winner == 1)
	    win = "O";
	// pops up a panel asking to play again and states the winner
	int reply = JOptionPane.showConfirmDialog(null,
	        "Congratulations player " + win
	                + "! You have won! \n Play again?", "Play again?",
	        JOptionPane.YES_NO_OPTION);
	// if yes, setup game and refresh panel
	if (reply == JOptionPane.YES_OPTION) {
	    game.startGame();
	    repaint();
	} // otherwise quit panel
	else
	    System.exit(0);
    }

    /**
     * A option box pops up when there is a tie, with no winner
     */
    public void tieGame() {
	// pops up a panel asking to play again, says no winner
	int reply = JOptionPane.showConfirmDialog(null,
	        "No winner this time. \n Play again?", "Play again?",
	        JOptionPane.YES_NO_OPTION);
	// if yes, setup game and refresh panel
	if (reply == JOptionPane.YES_OPTION) {
	    game.startGame();
	    repaint();
	} // otherwise quit panel
	else
	    System.exit(0);
    }

    /**
     * Mouse is clicked, and the x and y values are translated into a box number
     * and a move is made
     */
    public void mouseClicked(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
	int col = x / blockSize;
	int row = y / blockSize;
	int boxNum = row * 3 + col;
	game.move(boxNum);
	repaint();

	// if win or tie, end game, display corresponding box numbers
	if (game.checkWin() != 0)
	    congratsBox(game.checkWin());
	else if (game.getMoveNum() == 9)
	    tieGame();
    }

    @Override
    public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub

    }

}
