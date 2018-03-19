package lab_1.jug;

public class jug {
    public static void execute() {
        System.out.println("Jug:");

        State.setParameters(8, 6, 2);
        Node n = Node.BFS(new State(0, 0));
        if(n != null)
            Node.printPath(n);
        else
            System.out.println("No Solution");
    }
}
