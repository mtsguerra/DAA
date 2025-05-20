import java.util.*;

class Edgess {
    int neighbor;
    int capacity;
    Edgess(int neighbor, int capacity) {
        this.neighbor = neighbor;
        this.capacity = capacity;
    }
}

class preD3{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nEdges = input.nextInt();
        List<Edgess> [] graph = new ArrayList[nNodes+1];
        for (int i=1;i<=nNodes;i++){
            graph[i] = new ArrayList<>();
        }
        int[] leadTo = new int[nNodes+1];
        for (int i=0;i<nEdges;i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int capacity = input.nextInt();
            graph[node1].add(new Edgess(node2,capacity));
            leadTo[node2]++;
        }

        List<Integer> topologicalOrder = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] earliestTime = new int[nNodes+1];
        for (int i=0;i<leadTo.length;i++){
            if (leadTo[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int current = queue.poll();
            topologicalOrder.add(current);
            for (Edgess edge : graph[current]){
                if (earliestTime[edge.neighbor]  < earliestTime[current] + edge.capacity){
                    earliestTime[edge.neighbor] = earliestTime[current] + edge.capacity;
                }
                leadTo[edge.neighbor]--;
                if (leadTo[edge.neighbor] == 0){
                    queue.add(edge.neighbor);
                }
            }
        }

        for (int i=1;i<=nNodes;i++){
            System.out.print(earliestTime[i] + " ");
        }

    }

}