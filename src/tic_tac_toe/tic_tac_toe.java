package tic_tac_toe;

public class tic_tac_toe {
    public static void execute(){
        State.set_parameters(3, 'X', 'O');
        AdversarialSearch.play();
    }
}
