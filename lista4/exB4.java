import java.util.*;

class exB4 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int minSupported = input.nextInt();
        int maxSupported = input.nextInt();
        int startPoint = input.nextInt();
        int endPoint = input.nextInt();
        int nNodes = input.nextInt();
        int nBranches = input.nextInt();
        HashMap<Integer, HashMap<Integer,int[]>> map = new HashMap<>();
        for (int i=1;i<=nNodes;i++){
            map.put(i, new HashMap<Integer, int[]>());
        }
        for (int i=0;i<nBranches;i++){
            int node1= input.nextInt();
            int node2= input.nextInt();
            int expectedTemperature = input.nextInt();
            int price = input.nextInt();
            map.get(node1).put(node2, new int[]{expectedTemperature, price});
            map.get(node2).put(node1, new int[]{expectedTemperature, price});
        }
        int ans = possiblePaths(startPoint, endPoint, map, minSupported, maxSupported);
        if (ans == Integer.MAX_VALUE){
            System.out.println("Nao");
        }
        else {
            System.out.println("Sim " + ans);
        }
    }
    private static int possiblePaths(int startPoint, int endPoint, HashMap<Integer, HashMap<Integer,int[]>> map,
                                     int minSupported, int maxSupported){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[map.size()+1];
        int[] distances = new int[map.size()+1];

        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[startPoint] = 0;
        visited[startPoint] = true;
        queue.offer(startPoint);

        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            if (currentNode == endPoint){
                return distances[currentNode];
            }
            for (int nextNode : map.get(currentNode).keySet()){
                int temperature = map.get(currentNode).get(nextNode)[0];
                if (!visited[nextNode] && temperature >= minSupported && temperature <= maxSupported){
                    visited[nextNode] = true;
                    distances[nextNode] = distances[currentNode] + 1;
                    queue.offer(nextNode);
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}