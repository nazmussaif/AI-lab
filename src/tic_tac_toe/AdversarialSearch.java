package tic_tac_toe;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AdversarialSearch {

    static Random random = new Random();
    //int alpha = Integer.MAX_VALUE, beta = Integer.MIN_VALUE;

    static int max_value(State state) {

        // Complete here
        if(state.terminal_test())
            return state.utility();

        ArrayList<Integer> actions = state.get_actions();
        int v = Integer.MIN_VALUE;
        for (int a : actions){
            v = Math.max(min_value(state.apply_action(a)), v);

            //optimization
            if(v == 1) break;
        }

        return v;
    }

    static int min_value(State state) {

        // Complete here
        if(state.terminal_test())
            return state.utility();

        ArrayList<Integer> actions = state.get_actions();
        int v = Integer.MAX_VALUE;
        for (int a : actions) {
            v = Math.min(max_value(state.apply_action(a)), v);

            //optimization
            if(v == -1) break;
        }

        return v;
    }

    public static int minimax_decision(State state) {

        ArrayList<Integer> actions = state.get_actions();

        int minimaxValue = max_value(state);
        ArrayList<Integer> optimalActions = new ArrayList<>();

        for (int a : actions)
            if (min_value(state.apply_action(a)) == minimaxValue)
                optimalActions.add(a);

        // System.out.println("Minimax val = " + minimaxValue);

        int ac = random.nextInt(optimalActions.size());
        return optimalActions.get(ac);
    }

    static boolean OK(int a){
        return (a >= 0 && a < State.dimension);
    }

    public static void play() {
        Scanner input = new Scanner(System.in);
        State state = new State();

        while (!state.terminal_test()) {
            System.out.println("\n--------------------\n");
            System.out.println("Cuurent board:\n" + state);
            System.out.println("Player " + state.get_player() + "'s move");

            if (state.get_player() == 0) {
                int action = minimax_decision(state);
                state = state.apply_action(action);

                // System.out.println("Decision = " + action);
                // System.out.println("New board\n" + state);

            } else {
                System.out.println("Enter move coordinates: ");
                String move = input.nextLine();

                while (move.length() != 2 || !OK((move.charAt(0) - '0')) || !OK((move.charAt(1) - '0')) || state.board[(move.charAt(0) - '0')][(move.charAt(1) - '0')] != '\0'){
                    System.out.println("Wrong move!!!");
                    System.out.println("Enter move coordinates: ");
                    move = input.nextLine();
                }

                int action = (move.charAt(0) - '0') * State.dimension + (move.charAt(1) - '0');
                state = state.apply_action(action);
            }
        }

        System.out.println(state);
        System.out.println("End game utility = " + state.utility());

        input.close();
    }
}