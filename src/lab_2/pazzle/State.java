package lab_2.pazzle;

class State {
    int board[][] = new int[3][3];
    int bx, by;

    State(){
        int k = 8;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = k;
                k--;
            }
        }
    }

    State up(){
        int t[][] = new int[3][3];
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                t[i][j] = board[i][j];
            }
        }


    }

    boolean goal(){

    }
    public String toString(){
        return  "->> ";
    }
}
