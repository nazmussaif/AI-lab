package tic_tac_toe;

import java.util.ArrayList;

public class State {

	static char player0 = 'X', player1 = 'O';
	static int dimension = 3;
	char board[][];
	int turnsPlayed = 0;
	int utility_info = 0;
    int alpha = Integer.MIN_VALUE, beta = Integer.MAX_VALUE;

	public static void set_parameters(int dimension, char player0, char player1) {
		State.dimension = dimension;
		State.player0 = player0;
		State.player1 = player1;
	}

	public State() {
		super();

		board = new char[dimension][dimension];
		turnsPlayed = 0;
	}

	public State(State s) {
		super();

		board = new char[dimension][dimension];
		for (int i = 0; i < dimension; ++i)
			for (int j = 0; j < dimension; ++j)
				board[i][j] = s.board[i][j];

		turnsPlayed = s.turnsPlayed;
	}

	public int get_player() {
		// Complete here
		return turnsPlayed&1;
	}

	public boolean terminal_test() {

        // Complete here
        int player0_move, player1_move;
        for (int i = 0; i < dimension; ++i) {
            player0_move = 0;
            player1_move = 0;
            for (int j = 0; j < dimension; ++j) {
                if (board[i][j] == player0) player0_move++;
                else if (board[i][j] == player1) player1_move++;
                else break;
            }
            if (player0_move == dimension || player1_move == dimension){
                utility_info = player0_move == dimension ? 1 : -1;
                return true;
            }
        }

        for (int j = 0; j < dimension; ++j) {
            player0_move = 0;
            player1_move = 0;
            for (int i = 0; i < dimension; ++i) {
                if (board[i][j] == player0) player0_move++;
                else if (board[i][j] == player1) player1_move++;
                else break;
            }
            if (player0_move == dimension || player1_move == dimension){
                utility_info = player0_move == dimension ? 1 : -1;
                return true;
            }
        }

        player0_move = 0;
        player1_move = 0;
        for (int i = 0, j = i; i < dimension; ++i, ++j) {
            if (board[i][j] == player0) player0_move++;
            else if (board[i][j] == player1) player1_move++;
            else break;
        }
        if (player0_move == dimension || player1_move == dimension){
            utility_info = player0_move == dimension ? 1 : -1;
            return true;
        }

        player0_move = 0;
        player1_move = 0;
        for (int i = 0, j = dimension - 1; i < dimension; ++i, j--) {
            if (board[i][j] == player0) player0_move++;
            else if (board[i][j] == player1) player1_move++;
            else break;
        }
        if (player0_move == dimension || player1_move == dimension){
            utility_info = player0_move == dimension ? 1 : -1;
            return true;
        }

        //checking if all the moves have been made or not
        return turnsPlayed == 9;
    }

	public int utility() {

		// Complete here
        //utility_info is declared in line no 11 and evaluated in terminal_test();
        return utility_info;
	}

	public ArrayList<Integer> get_actions() {
		ArrayList<Integer> actions = new ArrayList<>();

		// Complete here
        for(int i = 0; i < dimension; ++i) {
            for (int j = 0; j < dimension; ++j) {
                if(board[i][j] == '\0')
                    actions.add(i*dimension+j);
            }
        }
		return actions;
	}

	public State apply_action(int action) {
		State nextState = new State(this);

		// Complete here
        int i = action/dimension;
        int j = action%dimension;
        if(turnsPlayed%2 == 0)
            nextState.board[i][j] = player0;
        else
            nextState.board[i][j] = player1;

        nextState.turnsPlayed++;

		return nextState;
	}

	public String toString() {

		StringBuilder str = new StringBuilder();

		for (int i = 0; i < dimension; ++i) {

			str.append("-");
			for (int j = 0; j < dimension; ++j)
				str.append("----");
			// str.append("-");

			str.append("\n|");

			for (int j = 0; j < dimension; ++j) {
				str.append(" ");
				str.append(board[i][j] != '\0' ? board[i][j] : " ");
				str.append(" |");
			}

			str.append("\n");
		}

		str.append("-");
		for (int j = 0; j < dimension; ++j)
			str.append("----");
		// str.append("-");

		return str.toString();
	}
}
