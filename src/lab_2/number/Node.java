package lab_2.number;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    private State state;
    private Node parent;
    private int cost;
    private String step;

    private Node(State state, Node parent, String s, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
        this.step = s;
    }

//    static Node BFS(State s) {
//        Queue<Node> Q = new LinkedList<>();
//        Q.add(new Node(s, null, "", 0));
//
//        while (!Q.isEmpty()) {
//            Node u = Q.poll();
//
//            if (u.state.goal()) {
//                return u;
//            }
//
//            State v;
//            v = u.state.plus();
//            Q.add(new Node(v, u, "+1", u.cost + 1));
//
//            v = u.state.minus();
//            Q.add(new Node(v, u, "-1", u.cost + 1));
//
//            v = u.state.mul();
//            Q.add(new Node(v, u, "x2", u.cost + 1));
//
//            v = u.state.div();
//            Q.add(new Node(v, u, "/2", u.cost + 1));
//        }
//        return null;
//    }

    static void printPath(Node n) {
        if (n.parent == null) {
            System.out.println(n);
            return;
        }
        printPath(n.parent);
        System.out.println(n);
    }

    @Override
    public String toString() {
        return state.toString() + " (" + step + ")";
    }
}
