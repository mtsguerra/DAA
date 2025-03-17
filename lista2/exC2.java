import java.util.*;

class exC2 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfStores = input.nextInt();
        input.nextLine();
        int[] numberOfSantas = new int[numberOfStores];
        for (int i=0;i<numberOfStores;i++){
            numberOfSantas[i] = input.nextInt();
        }
        input.nextLine();

        int numberOfBranches = input.nextInt();
        input.nextLine();

        HashMap<Integer,HashSet<Integer>> branches = new HashMap<>();
        for (int i=1;i<=numberOfStores;i++){
            branches.put(i,new HashSet<>());
        }

        for (int i=0;i<numberOfBranches;i++){
            int start = input.nextInt();
            int end = input.nextInt();
            branches.get(start).add(end);
            branches.get(end).add(start);
            input.nextLine();
        }

        int currentStore = input.nextInt();
        input.nextLine();
        int maxDistance = input.nextInt();
        input.nextLine();

        if (numberOfSantas[currentStore-1] > 0){
            System.out.println("Que sorte");
            return;
        }
        else{
            boolean[] visited = new boolean[numberOfStores+1];
            int possibleStores = findStore(currentStore,branches,numberOfSantas,maxDistance,visited);
            System.out.println(possibleStores);
        }

    }

    private static int findStore(int start, HashMap<Integer,HashSet<Integer>> branches,
                                   int [] numberOfSantas, int maxDistance, boolean[] visited){
        int nStores = 0;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{start,0});
        visited[start] = true;
        int currentDistance = 0;

        while (!q.isEmpty()){
            int[] current = q.poll();
            int node = current[0];
            int distance = current[1];

            if (distance>maxDistance) continue;
            if (numberOfSantas[node-1] > 0) nStores++;
            for (Integer neighbor : branches.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    q.add(new int[]{neighbor, distance + 1});
                }
            }
        }

        return nStores;
    }

}