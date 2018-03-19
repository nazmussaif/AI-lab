package lab_1.jug;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    private State state;
    private Node parent;
    private int cost;

    private Node(State state, Node parent, int cost) {
        this.state = state;
        this.parent = parent;
        this.cost = cost;
    }

    static Node BFS(State s) {
        Queue<Node> Q = new LinkedList<>();
        Q.add(new Node(s, null, 0));

        while (!Q.isEmpty()) {
            Node u = Q.poll();

            if (u.state.goal()) {
                return u;
            }

            State v;
            v = u.state.empty(1);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }

            v = u.state.empty(2);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }

            v = u.state.fill(1);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }

            v = u.state.fill(1);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }

            v = u.state.transfer(1);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }

            v = u.state.transfer(2);
            if (v != null) {
                Q.add(new Node(v, u, u.cost + 1));
            }
        }
        return null;
    }

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
        return state.toString();
    }
}
