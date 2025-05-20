import java.util.*;

class edededg{
    int node;
    int places;
    public edededg(int node, int places){
        this.node = node;
        this.places = places;
    }
}

public class preA5 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int minElements = input.nextInt();
        int origin = input.nextInt();
        int maxElements = input.nextInt();

        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<edededg> [] graph = new ArrayList[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nEdges; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int avaiableSeats = input.nextInt();
            graph[node1].add(new edededg(node2, avaiableSeats));
        }

        int [] possibles = possibles(graph, origin, nNodes, maxElements, minElements);
        boolean none = true;
        for (int i=1;i<possibles.length;i++){
            if (i==origin || possibles[i] < minElements) continue;
            none = false;
            System.out.println("Destino " + i + ": " + possibles[i]);
        }
        if (none) System.out.println("Impossivel");

    }

    private static int[] possibles (List<edededg> [] graph, int from, int nNodes, int maxElements, int minElements) {
        int[] possibles = new int[nNodes+1];
        Arrays.fill(possibles, Integer.MIN_VALUE);
        possibles[from] = maxElements;

        PriorityQueue<edededg> pq = new PriorityQueue<>(Comparator.comparingInt((edededg e) -> e.places).reversed());
        pq.offer(new edededg(from, maxElements));

        while (!pq.isEmpty()) {
            edededg current = pq.poll();
            for (edededg neighbour : graph[current.node]) {
                int newPlaces = Math.min(current.places, neighbour.places);
                if (newPlaces < minElements) continue;
                if (newPlaces > possibles[neighbour.node]) {
                    possibles[neighbour.node] = newPlaces;
                    pq.offer(new edededg(neighbour.node, newPlaces));
                }
            }
        }
        return possibles;
    }

}
