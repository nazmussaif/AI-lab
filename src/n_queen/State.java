package n_queen;

import java.util.Arrays;
import java.util.Random;

public class State {
	static int n;
	int[] r;

	public static void set_parameters(int dimension) {
		n = dimension;
	}

	public State() {
		r = new int[n];
	}

	public State(State anotherState) {

		r = new int[n];
		for (int i = 0; i < n; ++i)
			r[i] = anotherState.r[i];
	}

	public int objective_function() {

		int conflicts = 0;

		for (int i = 0; i < n; ++i)
			for (int j = i + 1; j < n; ++j)
				if (r[i] == r[j] || Math.abs(r[i] - r[j]) == Math.abs(i - j))
					conflicts++;

		return conflicts;
	}

	public State get_best_successor() {
		State bestSuccessor = null;
		int bestVal = Integer.MAX_VALUE;

		for (int queenNumber = 0; queenNumber < n; ++queenNumber)
			for (int newRow = 0; newRow < n; ++newRow) {

				State successor = new State(this);
				successor.r[queenNumber] = newRow;

				if (bestVal > successor.objective_function()) {
					bestSuccessor = successor;
					bestVal = successor.objective_function();
				}
			}

		return bestSuccessor;
	}

	public static State get_random_state() {
		State state = new State();

		Random random = new Random();

		for (int i = 0; i < n; ++i)
			state.r[i] = random.nextInt(n);

		return state;
	}

	public State get_rendom_successor(){
        Random random = new Random();
        State successor = new State(this);
        successor.r[random.nextInt(n)] = random.nextInt(n);

        return successor;
    }

	public String toString() {
		return Arrays.toString(r);
	}
}
