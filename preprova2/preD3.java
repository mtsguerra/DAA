import java.util.*;

class Edgess {
    int from;
    int neighbor;
    int capacity;
    Edgess( int from, int neighbor, int capacity) {
        this.from = from;
        this.neighbor = neighbor;
        this.capacity = capacity;
    }
}

class preD3{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<Edgess> [] graph = new ArrayList[nNodes+1];
        List<Edgess> edges = new ArrayList<>();

        for (int i=1;i<=nNodes;i++){
            graph[i] = new ArrayList<>();
        }
        int[] leadTo = new int[nNodes+1];
        for (int i=0;i<nEdges;i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int capacity = input.nextInt();
            Edgess e = new Edgess(node1, node2, capacity);
            edges.add(e);
            graph[node1].add(e);
            leadTo[node2]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] earliestTime = new int[nNodes+1];
        for (int i=1;i<leadTo.length;i++){
            if (leadTo[i] == 0){
                queue.add(i);
            }
        }
        while (!queue.isEmpty()){
            int current = queue.poll();
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

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (Edgess edge : edges){
            int start = earliestTime[edge.from];
            int end = start + edge.capacity;
            map.put(start, map.getOrDefault(start, 0) + 1);
            map.put(end, map.getOrDefault(end, 0) - 1);
        }

        int running =0;
        int maxRunning = 0;
        int firstTime = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            int time = entry.getKey();
            int delta = entry.getValue();
            running += delta;
            if (running > maxRunning){
                maxRunning = running;
                firstTime = time;
            }
        }
        System.out.println(earliestTime[nNodes] + " " + maxRunning + " " + firstTime);
    }

}
