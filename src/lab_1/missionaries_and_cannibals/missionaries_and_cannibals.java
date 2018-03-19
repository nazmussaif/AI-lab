package lab_1.missionaries_and_cannibals;

import java.util.*;

public class missionaries_and_cannibals {
    public static void execute() {
        System.out.println("Missionaries and Cannibals:");
        State in = new State(3,3,0,0,'l');
        Node n = Node.BFS(in);
        if(n != null) Node.printPath(n);
        else System.out.println("No Solution");
    }
}
