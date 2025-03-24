import java.util.*;

class exA3 {

    static private final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nExamples = input.nextInt();
        for (int p=0;p<nExamples;p++) {
            int nNodes = input.nextInt();
            int nBranches = input.nextInt();
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int i=1;i<=nNodes;i++) {
                map.put(i,new HashSet<>());
            }
            for (int i=0;i<nBranches;i++){
                int node1 = input.nextInt();
                int node2 = input.nextInt();
                map.get(node1).add(node2);
            }
            boolean hasCycle = hasCycle(map, nNodes);

            // Changed output format to match expected format
            System.out.println("Graph #" + (p + 1) + ": hasCycle() = " +
                    (hasCycle ? "true" : "false"));
        }
    }

    private static boolean hasCycle(HashMap<Integer, HashSet<Integer>> map, int nNodes) {
        boolean[] visited = new boolean[nNodes + 1];
        boolean[] inPath = new boolean[nNodes + 1];

        for (int i = 1; i <= nNodes; i++) {
            if (!visited[i]) {
                if (findCycle(map, i, visited, inPath)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean findCycle(HashMap<Integer, HashSet<Integer>> map, int node,
                                     boolean[] visited, boolean[] inPath) {
        if (inPath[node]) return true;
        if (visited[node]) return false;
        visited[node] = true;
        inPath[node] = true;
        for (int neighbor : map.get(node)) {
            if (findCycle(map, neighbor, visited, inPath)) return true;
        }
        inPath[node] = false;
        return false;
    }
}