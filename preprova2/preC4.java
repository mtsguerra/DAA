import java.util.*;

class edgedg{
    int from;
    int to;
    int temperature;
    int price;
    public edgedg(int from, int to, int temperature, int price){
        this.from = from;
        this.to = to;
        this.temperature = temperature;
        this.price = price;
    }
}

public class preC4 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int minTemp = input.nextInt();
        int maxTemp = input.nextInt();
        int origin  = input.nextInt();
        int destination = input.nextInt();

        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<edgedg> [] graph = new ArrayList[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nEdges; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int temperature = input.nextInt();
            int price = input.nextInt();
            graph[node1].add(new edgedg(node1, node2, temperature, price));
            graph[node2].add(new edgedg(node2, node1, temperature, price));
        }

        int pricesToCheck = input.nextInt();
        for (int i = 0; i < pricesToCheck; i++) {
            int maxCost = input.nextInt();
            if (possible(graph, origin, destination, minTemp, maxTemp, maxCost)) System.out.println("Sim");
            else System.out.println("Nao");
        }

    }

    private static boolean possible (List<edgedg> [] graph, int origin, int destination, int minTemp, int maxTemp, int maxSpent){
        boolean[] visited = new boolean[graph.length];
        int[] minToGetThere = new int[graph.length];
        Arrays.fill(minToGetThere, Integer.MAX_VALUE);
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q.add(origin);
        q2.add(0);
        while (!q.isEmpty()){
            int node = q.poll();
            int crrSpent = q2.poll();
            visited[node] = true;
            for (edgedg e : graph[node]){
                if (crrSpent + e.price < minToGetThere[e.to]){
                    if (e.temperature < minTemp || e.temperature > maxTemp){
                        continue;
                    }
                    int toAdd = e.price + crrSpent;
                    if (e.to == destination && toAdd <= maxSpent){
                        return true;
                    }
                    minToGetThere[e.to] =toAdd;
                    q.add(e.to);
                    q2.add(toAdd);
                }
            }
        }
        return false;
    }

}
