import java.util.*;

class Iron{
    int to;
    int reverse;
    int weight;
    public Iron(int to, int reverse, int weight) {
        this.to = to;
        this.reverse = reverse;
        this.weight = weight;
    }
}

public class preB6 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int source = 1;
        int sink = nNodes ;
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<Iron>[] graph = new ArrayList[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nEdges; i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            int weight = 1;
            addEdge(graph, from, to, weight);
        }

        System.out.println(getMaxFlow(graph, source, sink));

    }

    private static void addEdge(List<Iron>[] graph, int from, int to, int weight) {
        graph[from].add(new Iron(to, graph[to].size(), weight));
        graph[to].add(new Iron(from, graph[from].size()-1, weight));
    }

    private static int getMaxFlow(List<Iron>[] graph, int source, int sink) {
        int flow =0;
        while (true){
            boolean [] visited = new boolean[graph.length];
            int temp = dfs(graph, source, Integer.MAX_VALUE, sink, visited);
            if (temp == 0) break;
            flow += temp;
        }
        return flow;
    }

    private static int dfs(List<Iron>[] graph, int node, int flow, int sink, boolean[] visited) {
        if (node == sink) return flow;
        visited[node] = true;
        for (Iron edge : graph[node]) {
            if (!visited[edge.to] && edge.weight > 0) {
                int currentFlow = Math.min(edge.weight, flow);
                int temp = dfs(graph, edge.to, currentFlow, sink, visited);
                if (temp != 0) {
                    edge.weight -= temp;
                    graph[edge.to].get(edge.reverse).weight += temp;
                    return temp;
                }
            }
        }
        return 0;
    }

}
