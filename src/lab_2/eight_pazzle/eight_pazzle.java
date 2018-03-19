package lab_2.eight_pazzle;

import java.util.ArrayList;
import java.util.Collections;

public class eight_pazzle {
    //    change the value of N for NxN board
    static final int N = 3;
    public static void execute() {
        System.out.println("Eight Pazzle: ");

//	    this is for the sample test case
//        int goal[][] = {{1,2,3}, {4,5,6},{7,8,0}};

// 		this is for 4x4 board test, (change N = 4 before this)
//        int goal[][] = {{2, 1, 15, 14}, {4, 5, 0, 11}, {9, 3, 7, 8}, {13, 6, 12, 10}}; //cost should be 48

        int goal[][] = generate_random_board();
        State.initializeGoal(goal);

//	    this is for the sample test case
//        int board[][] = {{7, 2, 4}, {5, 0, 6}, {8, 3, 1}};

// 		this is for 4x4 board test, (change N = 4 before this)
//        int board[][] = {{10, 7, 8, 15}, {0, 2, 14, 9}, {13, 5, 6, 12}, {1, 4, 11, 3}};  //cost should be 48

        int board[][] = generate_random_board();
        State initial = new State(board);

        System.out.println("Random board: ");
        System.out.println(initial);

        System.out.println("Goal: ");
        for (int i = 0; i < N; ++i) {
            for (int a : goal[i]) {
                System.out.printf("%2d  ", a);
            }
            System.out.println();
        }

        Node path = Node.A_star(initial);
        if (path != null) {
            System.out.println("\nSolution sequence:\n");
            Node.print_soln(path);
        } else
            System.out.println("No solution found.");
    }

    private static int[][] generate_random_board() {
        int[][] board = new int[N][N];
        ArrayList<Integer> permutation = new ArrayList<>();

        for (int i = 0; i < (N * N); ++i)
            permutation.add(i);
        Collections.shuffle(permutation);

        int k = 0;
        for (int i = 0; i < N; ++i)
            for (int j = 0; j < N; ++j) {
                board[i][j] = permutation.get(k);
                k++;
            }
        return board;
    }
}
