package n_queen;

public class n_queen {
    public static void execute() {
        int n = 6;

        //State endState = LocalSearch.random_restart_hill_climbing(n);
        //State endState = LocalSearch.random_walk(n);
        State endState = LocalSearch.random_restart_first_choice_hcs(n);

        if(endState != null)
            System.out.println("Solution from first choice hcs: " + endState);
        else
            System.out.println("No Solution");
    }
}
