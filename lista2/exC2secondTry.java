import java.util.*;

class exC2secondTry{

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nStores = input.nextInt();
        int[] santas = new int[nStores+1];
        HashMap<Integer, HashSet<Integer>> branches = new HashMap<>();
        for (int i=1;i<=nStores;i++) {
            santas[i] = input.nextInt();
            branches.put(i, new HashSet<>());
        }
        int nBranches = input.nextInt();
        for (int i=0;i<nBranches;i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            branches.get(node1).add(node2);
            branches.get(node2).add(node1);
        }
        int currentStore = input.nextInt();
        int maxDistance = input.nextInt();
        if (santas[currentStore] >= 1) {
            System.out.println("Que sorte");
            return;
        }
        System.out.println(numberOfStores(currentStore, maxDistance, branches, santas));

    }
    private static int numberOfStores (int start, int maxDistance, HashMap<Integer, HashSet<Integer>> branches,
                                       int[] santas){
        Queue<int[]> queue = new LinkedList<>();
        boolean[] visited = new boolean[santas.length];
        queue.add(new int[]{start,0});
        visited[start] = true;
        int total =0;
        while (!queue.isEmpty()) {
            int[] crr = queue.poll();
            int currentStore = crr[0];
            int currentDistance = crr[1];
            if (santas[currentStore] >= 1 && currentDistance <= maxDistance) {
                total++;
            }
            for (int neighbor : branches.get(crr[0])) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(new int[]{neighbor, currentDistance+1});
                }
            }
        }
        return total;
    }
}