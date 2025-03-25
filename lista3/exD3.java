import java.util.*;

class exD3 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nBranches = input.nextInt();
        HashMap<Integer, HashSet<int[]>> map = new HashMap<>();
        for (int j=1;j<=nNodes;j++) {
            map.put(j,new HashSet<>());
        }
        int[] nEdges = new int[nNodes+1];
        for (int j=0;j<nBranches;j++){
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int duration = input.nextInt();
            map.get(node1).add(new int[]{node2,duration});
            nEdges[node2]++;
        }
        int ending =nNodes;
        for (int key : map.keySet()){
            if (map.get(key).isEmpty()){
                ending = key;
                break;
            }
        }
        int [] ans = solution(map, nNodes, nEdges, ending, nBranches);
        String temp = Arrays.toString(ans).replace("[", "")
                .replace("]", "")
                .replace(",", "");
        System.out.println(temp);
    }

    private static int[] solution(HashMap<Integer, HashSet<int[]>> map, int nNodes, int[] nEdges,
                                  int ending, int nBranches) {
        // minTime, maxSimultaneous, simTime
        int[] ans = new int[3];
        int[] timeAbs = new int[nNodes+1];
        Queue<int[]> inWorkTime = new LinkedList<>();
        for (int i=1;i<=nNodes;i++){
            if (nEdges[i] == 0){
                inWorkTime.add(new int[]{i,0});
                nEdges[i]=-1;
            }
        }


        int maxSimultaneous = 0;
        int simTime =0;
        int ins = 0;
        int toReach = nBranches;
        while (toReach>0){
            Queue<int[]> tempNodes = new LinkedList<>(inWorkTime);
            int lowestTime = Integer.MAX_VALUE;
            while (!tempNodes.isEmpty()){
                lowestTime = Math.min(lowestTime, tempNodes.poll()[1]);
            }
            int szWork = inWorkTime.size();
            while (szWork-->0){
                int[] crr = inWorkTime.poll();
                int crrNode = crr[0];
                int crrTime = crr[1];
                if (crrTime > lowestTime){
                    crr[1] -= lowestTime;
                    inWorkTime.add(crr);
                    continue;
                }
                else if (crrTime == lowestTime){
                    toReach--;
                    if (--nEdges[crrNode] > 0) continue;
                }
                for (int[] next : map.get(crrNode)){
                    int node = next[0];
                    int duration = next[1];
                    timeAbs[node] = Math.max(timeAbs[node], timeAbs[crrNode]+duration);
                    inWorkTime.add(new int[]{node,duration});
                    if (nEdges[node] == 0){
                        nEdges[node] = -1;
                    }
                }
            }
            int crrSize = inWorkTime.size();
            ins+=lowestTime;
            if (crrSize > maxSimultaneous){
                maxSimultaneous = crrSize;
                simTime = ins;
            }
        }
        ans[0] = timeAbs[ending];
        ans[1] = maxSimultaneous;
        ans[2] = simTime;
        return ans;
    }

}