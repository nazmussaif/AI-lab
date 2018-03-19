package lab_2.eight_pazzle;

import static java.lang.Math.abs;

public class State {
    private static int goal[][];
    private int board[][], bR, bC;

    static void initializeGoal(int[][] g) {
        goal = g;
    }

    State(int[][] board) {
        this.board = board;
        search_blank();
    }

    private void search_blank() {
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j)
                if (board[i][j] == 0) {
                    bR = i;
                    bC = j;
                    return;
                }
        }
    }

    public State move_up() {
        if (bR == 0) return null;

        int newBoard[][] = new int[eight_pazzle.N][eight_pazzle.N];
        for (int i = 0; i < eight_pazzle.N; ++i)
            System.arraycopy(board[i], 0, newBoard[i], 0, eight_pazzle.N);

        swap(newBoard, bR, bC, bR - 1, bC);
        return new State(newBoard);
    }

    public State move_down() {
        if (bR == eight_pazzle.N - 1) return null;

        int newBoard[][] = new int[eight_pazzle.N][eight_pazzle.N];
        for (int i = 0; i < eight_pazzle.N; ++i)
            System.arraycopy(board[i], 0, newBoard[i], 0, eight_pazzle.N);

        swap(newBoard, bR, bC, bR + 1, bC);
        return new State(newBoard);
    }

    public State move_left() {
        if (bC == 0) return null;

        int newBoard[][] = new int[eight_pazzle.N][eight_pazzle.N];
        for (int i = 0; i < eight_pazzle.N; ++i)
            System.arraycopy(board[i], 0, newBoard[i], 0, eight_pazzle.N);

        swap(newBoard, bR, bC, bR, bC - 1);
        return new State(newBoard);
    }

    public State move_right() {
        if (bC == eight_pazzle.N - 1) return null;

        int newBoard[][] = new int[eight_pazzle.N][eight_pazzle.N];
        for (int i = 0; i < eight_pazzle.N; ++i)
            System.arraycopy(board[i], 0, newBoard[i], 0, eight_pazzle.N);

        swap(newBoard, bR, bC, bR, bC + 1);
        return new State(newBoard);
    }

    int manhattan_distance() {
        int h = 0;
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j) {
                int value = board[i][j];
                if(value > 0) {
                    int[] pos = getGoalPosition(value);
                    h += abs(i - pos[0]) + abs(j - pos[1]);
                }
            }
        }
        return h;
    }

    int misplaced_tiles() {
        int h = 0;
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j) {
                int value = board[i][j];
                if(value > 0)
                    h += (value == goal[i][j]) ? 0 : 1;
            }
        }
        return h;
    }

    public int tiles_out_of_row_or_column() {
        int h = 0;
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j) {
                int value = board[i][j];
                if(value > 0) {
                    int[] pos = getGoalPosition(value);
                    h += (i == pos[0]) ? 0 : 1;
                    h += (j == pos[1]) ? 0 : 1;
                }
            }
        }
        return h;
    }

    private int[] getGoalPosition(int value) {
        int pos[] = new int[2];
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j) {
                if (goal[i][j] == value) {
                    pos[0] = i;
                    pos[1] = j;
                }
            }
        }
        return pos;
    }

    public boolean goal_test() {
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j) {
                if (board[i][j] != goal[i][j]) return false;
            }
        }
        return true;
    }

    private static void swap(int[][] a, int i, int j, int ni, int nj) {
        int t = a[i][j];
        a[i][j] = a[ni][nj];
        a[ni][nj] = t;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < eight_pazzle.N; ++i) {
            for (int j = 0; j < eight_pazzle.N; ++j)
                s.append(String.format("%2d  ", board[i][j]));
            s.append("\n");
        }
        return s.toString();
    }

    @Override
    public boolean equals(Object obj) {
        State rhs = (State) obj;
        for (int i = 0; i < eight_pazzle.N; ++i)
            for (int j = 0; j < eight_pazzle.N; ++j)
                if (board[i][j] != rhs.board[i][j])
                    return false;
        return true;
    }

    @Override
    public int hashCode() {
        long base = 101, M = 100000007;
        long hash = 0;
        for(int i = 0; i < eight_pazzle.N; ++i)
            for (int b : board[i]) {
                hash = (hash%M + (b*base)%M);
                base = base*base;
                if(base > M) base %= M;
            }
        return (int)(hash%M);
    }
}
