import java.util.*;

class Nighttrain {
    int node;
    public Nighttrain(int node) {
        this.node = node;
    }
}

public class preC2 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int[] storage = new int[nNodes+1];
        @SuppressWarnings("unchecked")
        List<Nighttrain>  [] graph = new ArrayList[nNodes+1];
        for (int i = 1; i <= nNodes; i++) {
            storage[i] = input.nextInt();
            graph[i] = new ArrayList<>();
        }
        int nEdges = input.nextInt();
        for (int i=0;i< nEdges;i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            addEdge(graph, from, to);
            addEdge(graph, to, from);
        }
        int currentStore = input.nextInt();
        int maxDistance = input.nextInt();

        int nStores = distanceBFS(graph, currentStore, maxDistance, storage);
        if (nStores == -7) System.out.println("Que sorte");
        else System.out.println(nStores);
    }

    private static void addEdge(List<Nighttrain>  [] graph, int from, int to) {
        graph[from].add(new Nighttrain(to));
    }

    private static int distanceBFS (List<Nighttrain> [] graph, int origin, int maxDistance, int[] storage) {
        if (storage[origin] > 0 ) return -7;
        int nStores = 0;
        boolean[] visited = new boolean[storage.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(origin);
        Queue<Integer> depth = new LinkedList<>();
        depth.add(0);
        visited[origin] = true;
        while (!q.isEmpty()) {
            int node = q.poll();
            int dep = depth.poll();
            visited[node] = true;
            if (dep == maxDistance) continue;
            for (Nighttrain n : graph[node]) {
                if (!visited[n.node]) {
                    visited[n.node] = true;
                    q.offer(n.node);
                    depth.add(dep+1);
                    if (storage[n.node] > 0) nStores++;
                }
            }
        }
        return nStores;
    }

}
