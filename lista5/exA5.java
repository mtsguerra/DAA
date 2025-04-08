import java.util.*;

class Node {
    int node;
    int places;
    public Node(int node, int places) {
        this.node = node;
        this.places = places;
    }
}

class exA5{

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int minElements = input.nextInt();
        int origin = input.nextInt();
        int maxElements = input.nextInt();

        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        HashMap<Integer, List<Node>> map = new HashMap<>();
        for (int i=1;i<=nNodes;i++){
            map.put(i, new ArrayList<>());
        }

        for (int i=0; i<nEdges; i++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int places = input.nextInt();
            map.get(node1).add(new Node(node2, places));
        }
        int paths = 0;
        int[] cityPlaces = maxPlaces(minElements, origin, 0, maxElements, map);
        for (int i=1;i<=nNodes;i++){
            if (i!=origin){
                int maxPlaces = cityPlaces[i];
                if (maxPlaces==Integer.MIN_VALUE) continue;
                System.out.println("Destino " + i + ": " + maxPlaces);
                paths++;
            }
        }
        if (paths==0){
            System.out.println("Impossivel");
        }
    }

    private static int[] maxPlaces (int minElements, int start, int destination,
                                  int maxElements, HashMap<Integer, List<Node>> map) {
        int[] cityPlaces = new int[map.size() + 1];
        Arrays.fill(cityPlaces, Integer.MIN_VALUE);
        cityPlaces[start] = maxElements;

        boolean[] visited = new boolean[map.size() + 1];

        PriorityQueue<Node> places =
                new PriorityQueue<>((a, b) -> Integer.compare(b.places, a.places));
        places.offer(new Node(start, maxElements));

        while (!places.isEmpty()) {
            Node current = places.poll();
            if (visited[current.node]) {
                continue;
            }
            visited[current.node] = true;
            if (current.places < cityPlaces[current.node]) {
                continue;
            }
            for (Node neighbor : map.get(current.node)) {
                int newPlaces = Math.min(current.places,neighbor.places);
                if (newPlaces < minElements) {
                    continue;
                }
                if (newPlaces > cityPlaces[neighbor.node]) {
                    cityPlaces[neighbor.node] = newPlaces;
                    places.offer(new Node(neighbor.node, newPlaces));
                }
            }
        }
        return cityPlaces;
    }
}