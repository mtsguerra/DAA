import java.util.*;

class Node{
    int neighbor;
    int distance;

    public Node(int neighbor, int distance) {
        this.neighbor = neighbor;
        this.distance = distance;
    }
}

class exD4second {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nStores = input.nextInt();
        int destiny = input.nextInt();

        HashMap<Integer, List<Node>> graph = new HashMap<>();
        for (int i =1; i <= nStores; i++) {
            graph.put(i, new ArrayList<>());
        }

        while (true){
            int node1 = input.nextInt();
            if (node1 == -1) break;
            int node2 = input.nextInt();
            int distance = input.nextInt();
            graph.get(node1).add(new Node(node2, distance));
            graph.get(node2).add(new Node(node1, distance));
        }
        int[] distances = calculateDistances(destiny, graph, nStores);

        Integer[] indexes = new Integer[nStores+1];
        for (int i=1; i <= nStores; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, 1, nStores+1,
            (ind1,ind2) -> Integer.compare(distances[ind1], distances[ind2]));
        String ans = Arrays.toString(indexes)
                .replace(" ", "")
                .replace(",", " ")
                .replace("[","")
                .replace("]","")
                .replace("null ", "");
        System.out.println(ans);
    }

    private static int[] calculateDistances(int startPoint, HashMap<Integer, List<Node>> graph,
                                            int nStores) {
        int[] distances = new int[nStores + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[startPoint] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.distance));
        pq.offer(new Node(startPoint, 0));

        while (!pq.isEmpty()){
            Node current = pq.poll();
            int currentNode = current.neighbor;
            int currentDistance = current.distance;
            if (currentDistance > distances[currentNode]) continue;
            for (Node neighbor : graph.get(currentNode)) {
                int newDist = currentDistance + neighbor.distance;
                if (newDist < distances[neighbor.neighbor]) {
                    distances[neighbor.neighbor] = newDist;
                    pq.offer(new Node(neighbor.neighbor, newDist));
                }
            }
        }
        return distances;
    }
}