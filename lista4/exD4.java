import java.util.*;

class exD4 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nStores = input.nextInt();
        int destiny = input.nextInt();
        // starting node -> neighbor -> distance
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        for (int i=1;i<=nStores;i++){
            map.put(i, new HashMap<Integer, Integer>());
        }
        while (true){
            int node1 = input.nextInt();
            if (node1 == -1) break;
            int node2 = input.nextInt();
            int distance = input.nextInt();
            map.get(node1).put(node2, distance);
            map.get(node2).put(node1, distance);
        }
        int[] distances = distances(destiny, map, nStores);
        Integer[] indexes = new Integer[distances.length];
        for (int i = 0; i < distances.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, new Comparator<Integer>() {
            @Override
            public int compare(Integer index1, Integer index2) {
                return Integer.compare(distances[index1], distances[index2]);
            }
        });
        for (int i =1;i < distances.length-1;i++) {
            System.out.print(indexes[i] + " ");
        }
        System.out.println(indexes[distances.length-1]);
    }

    private static int[] distances(int startPoint, HashMap<Integer, HashMap<Integer, Integer>> map,
                                   int nStores){
        int [] distances = new int[nStores+1];
        boolean[] visited = new boolean[nStores+1];
        // int[] -> node, distance
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startPoint, 0});
        visited[startPoint] = true;

        while (!queue.isEmpty()){
            int[] crr = queue.poll();
            int currentNode = crr[0];
            int currentDistance = crr[1];
            for (int neighbor : map.get(currentNode).keySet()){
                int distance = map.get(currentNode).get(neighbor);
                if ((!visited[neighbor] || currentDistance + distance < distances[neighbor])){
                    visited[neighbor] = true;
                    distances[neighbor] = currentDistance + distance;
                    queue.offer(new int[]{neighbor, distances[neighbor]});
                }
            }
        }
        return distances;
    }
}