package game;

/**
 * Game of Tic Tac Toe
 * 
 * @author Sophey
 *
 */
public class TicTacToeGame {

	// all the possible winning lines
	private static int[][][] checkWin = { { { 0 }, { 1, 2 }, { 3, 6 } },
			{ { 8 }, { 2, 5 }, { 6, 7 } },
			{ { 4 }, { 0, 8 }, { 1, 7 }, { 2, 6 }, { 3, 5 } } };
	private int[] board; // array of ints (0 empty, 1 O, 2 X) for the board
	private int moveNum; // move number

	/**
	 * Constructor for tic tac toe that starts the game
	 */
	public TicTacToeGame() {
		startGame();
	}

	/**
	 * Starts the game with a new empty board and reset move counter
	 */
	public void startGame() {
		board = new int[9];
		moveNum = 0;
	}

	/**
	 * Returns the move number
	 * 
	 * @return moveNum
	 */
	public int getMoveNum() {
		return moveNum;
	}

	/**
	 * Returns the board
	 * 
	 * @return board
	 */
	public int[] getBoard() {
		return board;
	}

	/**
	 * Puts an x or o on the board depending on the move number and the space
	 * Does not complete the move if the move number is greater than 9 or if the
	 * space is already full
	 * 
	 * @param moveSpace
	 *            box number
	 */
	public void move(int moveSpace) {
		if (moveNum == 9 || board[moveSpace] != 0)
			return;
		moveNum++;
		board[moveSpace] = moveNum % 2 + 1;
	}

	/**
	 * Checks if the board is won
	 * 
	 * @return winner (1 is O 2 is X) if there is one, 0 if not
	 */
	public int checkWin() {
		// goes through 3d array checkWin, first number is the move and the
		// subsequent arrays of 2 numbers are the ones that make a line with the
		// first
		for (int i = 0; i < checkWin.length; i++) {
			int move = board[checkWin[i][0][0]];
			for (int j = 1; j < checkWin[i].length; j++) {
				if (board[checkWin[i][j][0]] == move
						&& board[checkWin[i][j][1]] == move)
					return move;
			}
		}
		return 0;
	}

}
