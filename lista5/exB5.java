import java.util.*;

class Edge implements Comparable<Edge> {
    int u, v;
    int weight;
    public Edge(int u, int v, int weight) {
        this.u = u; this.v = v; this.weight = weight;
    }

    public int compareTo(Edge other) {
        return Integer.compare(other.weight, this.weight);
    }
}

class UnionFind {
    int[] parent, rank;

    public UnionFind(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    boolean union(int x, int y) {
        int rootX = find(x), rootY = find(y);
        if (rootX == rootY) return false;

        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootY] < rank[rootX]) {
            parent[rootY] = rootX;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        return true;
    }
}

public class exB5 {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int n = input.nextInt();
        int m = input.nextInt();
        int cost = input.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            int revenue = input.nextInt();
            int adjustedWeight = revenue - cost;
            edges[i] = new Edge(u, v, adjustedWeight);
        }
        Arrays.sort(edges);
        UnionFind uf = new UnionFind(n);
        int sum = 0;
        int count = 0;
        for (Edge e : edges) {
            if (uf.union(e.u, e.v)) {
                sum += e.weight;
                count++;
                if (count == n - 1) break;
            }
        }

        if (count == n - 1) {
            System.out.println("rendimento optimo: " + sum);
        } else {
            System.out.println("impossivel");
        }
    }
}