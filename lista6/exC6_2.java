import java.util.*;

class Edge {
    int to, reverse;
    int capacity;
    public Edge(int to, int reverse, int capacity) {
        this.to = to;
        this.reverse = reverse;
        this.capacity = capacity;
    }
}

class C6_2{
    private static final Scanner input = new Scanner(System.in);

    static class MaxFlow{
        List<Edge>[] graph;
        int source, sink;

        @SuppressWarnings("unchecked")
        public MaxFlow(int n, int source, int sink){
            this.source = source;
            this.sink = sink;
            graph = new ArrayList[n];
            for (int i=0;i<n;i++) graph[i] = new ArrayList<>();
        }

        public void addEdge(int from, int to, int capacity){
            graph[from].add(new Edge(to, graph[to].size(), capacity));
            graph[to].add(new Edge(from, graph[from].size()-1, 0));
        }

        public int dfs(int value, int flow, boolean[] visited){
            if (value==sink) return flow;
            visited[value] = true;
            for (Edge e : graph[value]){
                if (!visited[e.to] && e.capacity>0){
                    int currFlow = Math.min(flow, e.capacity);
                    int temp = dfs(e.to, currFlow, visited);
                    if (temp>0){
                        e.capacity -= temp;
                        graph[e.to].get(e.reverse).capacity += temp;
                        return temp;
                    }
                }
            }
            return 0;
        }

        public int getMaxFlow(){
            int flow = 0;
            while (true){
                boolean[] visited = new boolean[graph.length];
                int flowed = dfs(source, Integer.MAX_VALUE, visited);
                if (flowed==0) break;
                flow += flowed;
            }
            return flow;
        }
    }

    public static void main(String[] args) {
        int numCases = input.nextInt();
        for (int i=0;i<numCases;i++){
            int nPeople = input.nextInt();
            int nEdges = input.nextInt();
            int totalNodes = nPeople * 2 + 2;
            int source = totalNodes - 2;
            int sink = totalNodes - 1;

            MaxFlow mf = new MaxFlow(totalNodes, source, sink);

            for (int j=0;j<nPeople;j++){
                mf.addEdge(source, j, 1);
            }
            for (int j=0;j<nPeople;j++){
                mf.addEdge(j+nPeople, sink, 1);
            }

            for (int j=0;j<nEdges;j++){
                int node1 = input.nextInt();
                int node2 = input.nextInt();
                mf.addEdge(node1, node2+nPeople, 1);
            }

            System.out.println(mf.getMaxFlow() == nPeople ? "YES" : "NO");

        }
    }
}