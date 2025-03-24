import java.util.*;

class exC3 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nGraphs = input.nextInt();
        for (int i=1;i<=nGraphs;i++){
            int nNodes = input.nextInt();
            int nBranches = input.nextInt();
            HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
            for (int j=1;j<=nNodes;j++) {
                map.put(j,new HashSet<>());
            }
            int[] nEdges = new int[nNodes+1];
            for (int j=0;j<nBranches;j++){
                int node1 = input.nextInt();
                int node2 = input.nextInt();
                map.get(node1).add(node2);
                nEdges[node2]++;
            }
            if (hasCycle(map, nNodes)) {
                System.out.println("Graph #" + i + ": not a DAG (has cycle)");
                continue;
            }
            int[][] ans = moreThanOneWay(map, nNodes, nEdges);
            if (ans[1][0] == 1) {
                String temp = Arrays.toString(ans[0]).replace("[", "")
                        .replace("]", "")
                        .replace(",", "");
                System.out.println("Graph #" + i + ": DAG with a single topological order: "+temp);
            }
            else{
                System.out.println("Graph #" + i + ": DAG with more than one topological order");
            }

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

    private static int[][] moreThanOneWay (HashMap<Integer, HashSet<Integer>> map, int nNodes,
                                           int[] nEdges) {
        int[][] ans = new int[2][nNodes];
        ans[1][0] = 1;
        nEdges[0] = -1;
        Queue<Integer> queue = new LinkedList<>();
        for (int i=1;i<=nNodes;i++){
            if (nEdges[i]==0){
                queue.add(i);
                nEdges[i] = -1;
            }
        }
        if (queue.size()>1) ans[1][0] =0;
        int index=0;
        while (!queue.isEmpty()){
            int current = queue.poll();
            ans[0][index] = current;
            index++;
            for (int neighbor : map.get(current)){
                nEdges[neighbor]--;
                if (nEdges[neighbor]==0){
                    queue.add(neighbor);
                    nEdges[neighbor] = -1;
                }
            }
            if (queue.size() > 1) ans[1][0] = 0;
        }
        return ans;
    }
}