import java.util.*;

class exB2secondTry{

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nSupermarkets = input.nextInt();
        int[] pumpkins = new int[nSupermarkets+1];
        HashMap<Integer, HashSet<Integer>> branches = new HashMap<>();
        for (int i=1;i<=nSupermarkets;i++) {
            pumpkins[i] = input.nextInt();
            branches.put(i, new HashSet<>());
        }
        int nBranches = input.nextInt();
        for (int i=0;i<nBranches;i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            branches.get(node1).add(node2);
            branches.get(node2).add(node1);
        }
        int nClients = input.nextInt();
        for (int i=0;i<nClients;i++){
            int currentSupermarket = input.nextInt();
            if (pumpkins[currentSupermarket] >= 1) {
                System.out.println(currentSupermarket);
                continue;
            }
            int idealSupermarket = findIdealSuper(currentSupermarket, branches, pumpkins);
            if (idealSupermarket == -1) System.out.println("Impossivel");
            else System.out.println(idealSupermarket);
        }
    }

    private static int findIdealSuper(int start, HashMap<Integer, HashSet<Integer>> branches,
                                      int[] pumpkins){
        int[] bestOne = new int[2];
        bestOne[0] = Integer.MIN_VALUE;
        bestOne[1] = -1;
        boolean[] visited = new boolean[pumpkins.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            if (pumpkins[current] >= 1 && (pumpkins[current] >= bestOne[0])) {
                if (pumpkins[current] == bestOne[0]) {
                    bestOne[1] = Math.min(bestOne[1], current);
                }
                else {
                    bestOne[0] = pumpkins[current];
                    bestOne[1] = current;
                }
            }
            for (int neighbor : branches.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return bestOne[1];
    }
}