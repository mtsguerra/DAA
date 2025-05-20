import java.util.*;

class angelo{
    int to;
    int reverse;
    int weight;
    public angelo(int to, int reverse, int weight){
        this.to = to;
        this.reverse = reverse;
        this.weight = weight;
    }
}

class preC6again {

    private static final Scanner input = new Scanner(System.in);

     public static void main(String[] args) {
         int nCases = input.nextInt();
         for (int p=0;p<nCases;p++){
             int nPeople = input.nextInt();
             int nNodes = nPeople*2 + 2;
             int source = nNodes-2;
             int sink = nNodes-1;
             int nEdges = input.nextInt();

             @SuppressWarnings("unchecked")
             List<angelo> [] graph = new ArrayList[nNodes];

             for (int i=0;i<nNodes;i++){
                 graph[i] = new ArrayList<>();
             }

             for (int i=0;i<nPeople;i++){
                addEdge(graph, source, i, 1);
             }

             for (int i=0;i<nPeople;i++){
                 addEdge(graph, i+nPeople, sink, 1);
             }

             for (int i=0;i<nEdges;i++){
                int node1 = input.nextInt();
                int node2 = input.nextInt();
                int weight = 1;
                addEdge(graph, node1, node2+nPeople, weight);
             }

             int maxFlow = findMaxFlow(graph, source, sink);
             if (maxFlow == nPeople) System.out.println("YES");
             else System.out.println("NO");
         }
     }

     private static void addEdge(List<angelo> [] graph, int from, int to, int weight){
         graph[from].add(new angelo(to, graph[to].size(), weight));
         graph[to].add(new angelo(from, graph[from].size()-1, 0));
     }

     private static int findMaxFlow (List<angelo> [] graph, int source, int sink){
         int flow=0;
         while (true){
             boolean[] visited = new boolean[graph.length];
             int temp = dfs(graph, source, Integer.MAX_VALUE, sink, visited);
             if (temp == 0) break;
             flow +=  temp;
         }
         return flow;
     }

     private static int dfs (List<angelo> [] graph, int node, int flow, int sink, boolean[] visited){
         if (node == sink) return flow;
         visited[node] = true;
         for (angelo e : graph[node]){
            if (!visited[e.to] && e.weight > 0){
                int currentFlow = Math.min(flow, e.weight);
                int temp = dfs(graph, e.to, currentFlow, sink, visited);
                if (temp > 0){
                    e.weight -= temp;
                    graph[e.to].get(e.reverse).weight += temp;
                    return temp;
                }
            }
         }
         return 0;
     }

}
