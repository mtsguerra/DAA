import java.util.*;

class exD2secondTry {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nBranches = input.nextInt();
        // dont use boolean
        HashMap<Integer, boolean[]> branches = new HashMap<>();
        for (int i=1;i<=nNodes;i++) {
            branches.put(i, new boolean[nNodes+1]);
        }
        for (int i=0;i<nBranches;i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            branches.get(node1)[node2] = true;
            branches.get(node2)[node1] = true;
        }
        int [] biggestNodes = precomputeBiggestNodes(nNodes, branches);
        int questions = input.nextInt();
        for (int i=0;i<questions;i++) {
            int question = input.nextInt();
            System.out.println("No "+question+": "+biggestNodes[question]);
        }
    }

    private static int[] precomputeBiggestNodes(int nNodes,HashMap<Integer, boolean[]> branches){
        int[] biggestNodes = new int[nNodes+1];
        boolean[] visited = new boolean[nNodes+1];
        for (int i=1;i<=nNodes;i++) biggestNodes[i] = i;
        for (int i=1;i<=nNodes;i++) {
            if (!visited[i]) {
                int biggest = i;
                Queue<Integer> queue = new LinkedList<>();
                List<Integer> cycle = new ArrayList<>();
                queue.add(i);
                cycle.add(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    biggest = Math.max(biggest,current);
                    for (int j=1;j<=nNodes;j++) {
                        if (branches.get(current)[j] && !visited[j]) {
                            visited[j] = true;
                            queue.add(j);
                            cycle.add(j);
                        }
                    }
                }
                for (int n : cycle) {
                    biggestNodes[n] = biggest;
                }

            }
        }
        return biggestNodes;
    }


}