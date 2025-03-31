import java.util.*;

class exA4 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
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
        while (true){
            int sizePath = input.nextInt();
            if (sizePath ==0) break;
            int minTemp = Integer.MAX_VALUE;
            int maxTemp = Integer.MIN_VALUE;
            int prev = input.nextInt();
            for (int i = 0; i< sizePath -1; i++){
                int nxt = input.nextInt();
                minTemp = Math.min(minTemp,map.get(prev).get(nxt)[0]);
                maxTemp = Math.max(maxTemp,map.get(prev).get(nxt)[0]);
                prev = nxt;
            }
            System.out.println(minTemp + " " + maxTemp);
        }
    }
}