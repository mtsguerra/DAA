import java.util.*;

class exG2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfNodes = input.nextInt();
        int numberOfActivities = input.nextInt();
        int k = input.nextInt();

        // time spent to do all the activities
        int totalTime = 0;
        // remaining edges for each node
        int[] nEdges = new int[numberOfNodes+1];
        // connections between nodes
        HashMap<Integer, List<int[]>> branches = new HashMap<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            branches.put(i, new ArrayList<>());
        }

        for (int i=0;i<numberOfActivities;i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int weight = input.nextInt();
            totalTime += weight;
            branches.get(node1).add(new int[]{node2,weight});
            nEdges[node2]++;
        }
        KahnAlgorithm(branches,nEdges,numberOfActivities,numberOfNodes, totalTime, k);
    }

    private static void KahnAlgorithm (HashMap<Integer, List<int[]>> connections,
                                      int [] nEdges, int numberOfActivities, int numberOfNodes,
                                       int totalTime, int k){
        Queue<Integer> queue = new LinkedList<>();
        // initial topological order (excluding k)
        for (int i = 1; i <= numberOfNodes; i++) {
            if (nEdges[i] == 0 && i != k) {
                queue.add(i);
                nEdges[i] = -1;
            }
        }
        while (!queue.isEmpty()){
            int current = queue.poll();
            for (int[] neighbor : connections.get(current)){
                nEdges[neighbor[0]]--;
                // decreases the time spent by the weight of the edge
                totalTime -= neighbor[1];
                // add newly available nodes (excluding k)
                if (nEdges[neighbor[0]] == 0 && neighbor[0] != k) {
                    queue.add(neighbor[0]);
                    nEdges[neighbor[0]] = -1;
                }
            }
        }
        int remainingRelations = 0;
        for (int n : nEdges){
            if (n > 0){
                remainingRelations+=n;
            }
        }
        System.out.println(remainingRelations + " " + totalTime);
    }

}