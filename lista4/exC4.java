import java.util.*;

class exC4 {

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

        int tries = input.nextInt();
        for (int i=0;i<tries;i++){
            int currency = input.nextInt();
            if (isPossible(startPoint, endPoint, map, minSupported, maxSupported, currency)) {
                System.out.println("Sim");
            }
            else System.out.println("Nao");
        }
    }

    private static boolean isPossible (int startPoint, int endPoint, HashMap<Integer, HashMap<Integer,int[]>> map,
                                int minSupported, int maxSupported, int currency){
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[map.size()+1];
        int[] prices = new int[map.size()+1];

        visited[startPoint] = true;
        queue.offer(startPoint);

        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            int currentPrice = prices[currentNode];
            for (int neighbor : map.get(currentNode).keySet()){
                int temperature = map.get(currentNode).get(neighbor)[0];
                int price = map.get(currentNode).get(neighbor)[1];
                if (neighbor == endPoint){
                    if (currentPrice + price <= currency
                        && temperature <= maxSupported
                        && temperature >= minSupported) return true;
                    else continue;
                }
                // compute if this node was not visited before or if the price is lower than the previous one setted before
                if ((!visited[neighbor] || currentPrice + price < prices[neighbor]) && temperature >= minSupported
                        && temperature <= maxSupported
                        && currentPrice + price <= currency){
                    prices[neighbor] = currentPrice + price;
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return false;
    }
}