import java.util.*;

class exB3{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
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
        int nQuestions = input.nextInt();
        for (int i=0;i<nQuestions;i++){
            int[] order = new int[nNodes];
            for (int j=0;j<nNodes;j++){
                order[j] = input.nextInt();
            }
            System.out.println(isTopologicalOrder(order,map,nNodes));
        }
    }

    private static boolean isTopologicalOrder(int[] order, HashMap<Integer, HashSet<Integer>> map,
                                              int nNodes) {
        HashMap<Integer, HashSet<Integer>> reversed = reverse(map, nNodes);
        boolean[] visited = new boolean[nNodes + 1];
        for (int n : order){
            visited[n] = true;
            for (int neighbor : reversed.get(n)) {
                if (!visited[neighbor]) return false;
            }
        }
        return true;
    }

    private static HashMap<Integer, HashSet<Integer>> reverse(HashMap<Integer, HashSet<Integer>> map, int nNodes) {
        HashMap<Integer, HashSet<Integer>> reversed = new HashMap<>();
        for (int i=1;i<=nNodes;i++) {
            reversed.put(i, new HashSet<>());
        }
        for (int key : map.keySet()) {
            for (int value : map.get(key)) {
                reversed.get(value).add(key);
            }
        }
        return reversed;
    }

}