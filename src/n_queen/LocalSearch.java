package n_queen;

public class LocalSearch {

	public static State greedy_hill_climbing(int n) {
		
		State.set_parameters(n);

		State currentState = State.get_random_state();
		int currentObjVal = currentState.objective_function();

		while (true) {
			//System.out.println("curr " + currentObjVal);

			if (currentObjVal == 0)
				break;

			State bestSuccessor = currentState.get_best_successor();
			int successorVal = bestSuccessor.objective_function();
			
			if (currentObjVal <= successorVal)
				break;

			currentState = bestSuccessor;
			currentObjVal = successorVal;
		}

		return currentState;
	}

	public static State random_restart_hill_climbing(int n) {

		System.out.println("Running random restart hill climbing");

		State endState = null;

		while (true) {
			endState = greedy_hill_climbing(n);

			if (endState.objective_function() == 0)
				break;
		}

		return endState;
	}
}
