import java.util.*;

class exC6{
    private static final Scanner input = new Scanner(System.in);

    static class Edge {
        int to, rev;
        int capacity;
        public Edge(int to, int rev, int capacity) {
            this.to = to;
            this.rev = rev;
            this.capacity = capacity;
        }
    }

    static class MaxFlow {
        List<Edge>[] graph;
        int[] level, ptr;
        int source, sink;

        @SuppressWarnings("unchecked")
        public MaxFlow(int n, int source, int sink) {
            this.source = source;
            this.sink = sink;
            graph = new ArrayList[n];
            for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        }

        public void addEdge(int from, int to, int cap) {
            graph[from].add(new Edge(to, graph[to].size(), cap));
            graph[to].add(new Edge(from, graph[from].size() - 1, 0)); // reverse edge
        }

        boolean bfs() {
            level = new int[graph.length];
            Arrays.fill(level, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(source);
            level[source] = 0;

            while (!q.isEmpty()) {
                int v = q.poll();
                for (Edge e : graph[v]) {
                    if (e.capacity > 0 && level[e.to] == -1) {
                        level[e.to] = level[v] + 1;
                        q.add(e.to);
                    }
                }
            }
            return level[sink] != -1;
        }

        int dfs(int v, int pushed) {
            if (pushed == 0) return 0;
            if (v == sink) return pushed;

            for (; ptr[v] < graph[v].size(); ptr[v]++) {
                Edge e = graph[v].get(ptr[v]);
                if (level[e.to] == level[v] + 1 && e.capacity > 0) {
                    int tr = dfs(e.to, Math.min(pushed, e.capacity));
                    if (tr > 0) {
                        e.capacity -= tr;
                        graph[e.to].get(e.rev).capacity += tr;
                        return tr;
                    }
                }
            }
            return 0;
        }

        public int getMaxFlow() {
            int flow = 0;
            while (bfs()) {
                ptr = new int[graph.length];
                int pushed;
                while ((pushed = dfs(source, Integer.MAX_VALUE)) != 0) {
                    flow += pushed;
                }
            }
            return flow;
        }
    }

    public static void main(String[] args) {
        int nCases = input.nextInt();
        for (int z = 0; z < nCases; z++) {
            int nPeople = input.nextInt();
            int nEdges = input.nextInt();
            // 0 ... nPeople : people
            // nPeople ... 2*nPeople : books
            // 2*nPeople ... 2*nPeople+2 : source and sink
            int totalNodes = nPeople * 2 + 2;
            int source = totalNodes - 2;
            int sink = totalNodes - 1;

            MaxFlow mf = new MaxFlow(totalNodes, source, sink);

            for (int i = 0; i < nPeople; i++) {
                mf.addEdge(source, i, 1);
            }
            for (int i = 0; i < nPeople; i++) {
                mf.addEdge(i + nPeople, sink, 1);
            }
            for (int i = 0; i < nEdges; i++) {
                int person = input.nextInt();
                int book = input.nextInt();
                mf.addEdge(person, book + nPeople, 1);
            }
            int flow = mf.getMaxFlow();
            System.out.println(flow == nPeople ? "YES" : "NO");
        }
    }
}