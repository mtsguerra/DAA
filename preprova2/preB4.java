import java.util.*;

class eddd{
    int from;
    int to;
    int temperature;
    int price;
    public eddd(int from, int to, int temperature, int price){
        this.from = from;
        this.to = to;
        this.temperature = temperature;
        this.price = price;
    }
}

public class preB4 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int minTemp = input.nextInt();
        int maxTemp = input.nextInt();
        int origin  = input.nextInt();
        int destination = input.nextInt();

        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<eddd> [] graph = new ArrayList[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nEdges; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int temperature = input.nextInt();
            int price = input.nextInt();
            graph[node1].add(new eddd(node1, node2, temperature, price));
            graph[node2].add(new eddd(node2, node1, temperature, price));
        }

        int path = findPath(graph, origin, destination, minTemp, maxTemp);
        if (path == 0){
            System.out.println("Nao");
            return;
        }
        System.out.println("Sim " + path );

    }

    private static int findPath (List<eddd> [] graph, int origin, int destination, int minTemp, int maxTemp){
        boolean[] visited = new boolean[graph.length];
        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        q.add(origin);
        q2.add(1);
        while (!q.isEmpty()){
            int node = q.poll();
            int crrSize = q2.poll();
            visited[node] = true;
            for (eddd e : graph[node]){
                if (!visited[e.to]){
                    if (!(e.temperature >= minTemp && e.temperature <= maxTemp)){
                        continue;
                    }
                    if (e.to == destination){
                        return crrSize;
                    }
                    visited[e.to] = true;
                    q.add(e.to);
                    int toAdd = crrSize + 1;
                    q2.add(toAdd);
                }
            }
        }
        return 0;
    }

}
