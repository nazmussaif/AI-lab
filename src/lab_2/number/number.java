package lab_2.number;

/**
 * Created by common on 3/13/2018.
 */
public class number {
    public static void execute() {
        System.out.println("Jug:");

        State.setParameters(101);
        Node n = Node.BFS(new State(10));
        if (n != null)
            Node.printPath(n);
        else
            System.out.println("No Solution");
    }
}
