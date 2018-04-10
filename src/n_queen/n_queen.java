package n_queen;

public class n_queen {
    public static void execute() {
        int n = 50;

        State endState = LocalSearch.random_restart_hill_climbing(n);

        System.out.println("Solution from Random Hill Climbing " + endState);
        System.out.println("Final objective val = "
                + endState.objective_function());
    }
}
