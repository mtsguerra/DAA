import java.util.*;

class edgepoi {
    int to;
    int reverse;
    int weight;
    public edgepoi(int to, int reverse, int weight) {
        this.to = to;
        this.reverse = reverse;
        this.weight = weight;
    }
}

public class preC6 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nCases = input.nextInt();
        for (int i = 0; i < nCases; i++) {
            int nPeople = input.nextInt();
            int nNodes = nPeople * 2 + 2;
            int source = nNodes-2;
            int sink = nNodes-1;
            int nEdges = input.nextInt();

            @SuppressWarnings("unchecked")
            List<edgepoi> [] graph = new ArrayList[nNodes];

            for (int j=0;j<nNodes;j++){
                graph[j] = new ArrayList<>();
            }

            for (int j=0;j<nPeople;j++){
                addEdge(graph,source,j,1);
            }

            for (int j=0;j<nPeople;j++){
                addEdge(graph,j+nPeople,sink,1);
            }

            for (int j = 0; j < nEdges; j++) {
                int node1 = input.nextInt();
                int node2 = input.nextInt();
                int weight = 1;
                addEdge(graph,node1,node2+nPeople,weight);
            }

            int maxFlow = maxFlow(graph, source, sink);
            if (maxFlow == nPeople) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    static void addEdge(List<edgepoi> [] graph, int from, int to, int weight) {
        graph[from].add(new edgepoi(to, graph[to].size(), weight));
        graph[to].add(new edgepoi(from, graph[from].size()-1, 0));
    }

    private static int maxFlow (List<edgepoi> [] graph, int source, int sink) {
        int flow =0;
        while (true){
            boolean[] visited = new boolean[graph.length];
            int flowed = dfs(source, Integer.MAX_VALUE, graph, visited, sink);
            if (flowed == 0) break;
            flow += flowed;
        }
        return flow;
    }

    private static int dfs (int value, int flow, List<edgepoi> [] graph, boolean[] visited, int sink){
        if (value==sink) return flow;
        visited[value] = true;
        for (edgepoi edge : graph[value]) {
            if (!visited[edge.to] && edge.weight>0) {
                int currentFlow = Math.min(flow, edge.weight);
                int temp = dfs(edge.to, currentFlow, graph, visited, sink);
                if (temp >0){
                    edge.weight -= temp;
                    graph[edge.to].get(edge.reverse).weight += temp;
                    return temp;
                }
            }
        }
        return 0;
    }

}
