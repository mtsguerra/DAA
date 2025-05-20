import java.util.*;

class ededg{
    int to;
    int weight;
    public ededg(int to, int weight){
        this.to = to;
        this.weight = weight;
    }
}

public class preD4 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int destination = input.nextInt();
        @SuppressWarnings("unchecked")
        List<ededg> [] graph = new ArrayList[nNodes+1];
        for (int i=1; i<=nNodes; i++){
            graph[i] = new ArrayList<>();
        }
        while (true){
            int from = input.nextInt();
            if (from==-1) break;
            int to = input.nextInt();
            int weight = input.nextInt();
            graph[from].add(new ededg(to, weight));
            graph[to].add(new ededg(from, weight));
        }

        int[]distances = calcDistances(graph, destination, nNodes);
        Integer[] indexes = new Integer[distances.length];
        for (int i=0; i<distances.length; i++){
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(i->distances[i]));
        for (int i=0; i<distances.length-2; i++){
            System.out.print(indexes[i] + " ");
        }
        System.out.println(indexes[distances.length-2]);
    }

    private static int[] calcDistances (List<ededg> [] graph, int startingPoint, int nNodes){
        int[] distances = new int[nNodes+1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startingPoint] = 0;

        PriorityQueue<ededg> pq = new PriorityQueue<>(Comparator.comparingInt(e->e.weight));
        pq.offer(new ededg(startingPoint, 0));

        while (!pq.isEmpty()){
            ededg current = pq.poll();
            int node = current.to;
            int distance = current.weight;
            if (distance > distances[node]) continue;
            for (ededg n : graph[node]){
                int newDist = distance + n.weight;
                if (newDist < distances[n.to]) {
                    distances[n.to] = newDist;
                    pq.offer(new ededg(n.to, newDist));
                }
            }
        }
        return distances;
    }

}
