import java.util.*;

public class preA2 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nConnections = input.nextInt();
        @SuppressWarnings("unchecked")
        HashSet<Integer> [] connections = new HashSet[nNodes+1];
        for (int i=1;i<=nNodes;i++){
            connections[i] = new HashSet<>();
        }
        for (int i = 0; i < nConnections; i++) {
            int sz = input.nextInt();
            int initial = input.nextInt();
            for (int j = 0; j < sz-1; j++) {
                int next = input.nextInt();
                connections[initial].add(next);
                initial = next;
            }
        }
        for (int i=1;i<=nNodes;i++){
            System.out.println(connections[i].size());
        }
    }

}
