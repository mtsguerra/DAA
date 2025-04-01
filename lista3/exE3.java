import java.util.*;
class exE3{
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nBranches = input.nextInt();
        HashMap<Integer, HashSet<int[]>> map = new HashMap<>();
        int[] nEdges = new int[nNodes+1];
        for (int j=1;j<=nNodes;j++) {
            map.put(j,new HashSet<>());
        }
        for (int i = 0; i< nBranches; i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int time = input.nextInt();
            map.get(node1).add(new int[]{node2,time});
            nEdges[node2]++;
        }
        int nQuestions = input.nextInt();
        int[] timeConclusion = computeTime(nEdges, nBranches, map);
        for (int i = 0; i < nQuestions; i++){
            int question = input.nextInt();
            int concluded = 0;
            for (int n : timeConclusion) if (n <= question) concluded++;
            System.out.println(question+" "+concluded);
        }
    }

    private static int[] computeTime (int[] nEdges, int nBranches,
                                      HashMap<Integer, HashSet<int[]>> map){
        int[] timeActivity = new int[nBranches];
        Queue<Integer> queueNode = new LinkedList<>();
        Queue<Integer> queueTime = new LinkedList<>();
        queueNode.add(1);
        queueTime.add(0);
        int index =0;
        while (!queueNode.isEmpty()){
            int node = queueNode.poll();
            int timeNode = queueTime.poll();
            for (int[] nextNode : map.get(node)){
                int neighbor = nextNode[0];
                int timeNeighbor = nextNode[1];
                timeActivity[index++] = timeNode+ timeNeighbor;
                if (--nEdges[neighbor]==0){
                    queueNode.add(neighbor);
                    queueTime.add(timeNode+ timeNeighbor);
                }
            }
        }
        return timeActivity;
    }

}