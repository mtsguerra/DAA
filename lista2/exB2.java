import java.util.*;

class exB2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfSupermarkets = input.nextInt();
        input.nextLine();
        int[] numberOfPumpkins = new int[numberOfSupermarkets];
        HashMap<Integer, HashSet<Integer>> connections = new HashMap<>();

        for (int i=0;i<numberOfSupermarkets;i++){
            numberOfPumpkins[i] = input.nextInt();
            connections.put(i+1,new HashSet<>());
        }
        input.nextLine();
        int numberOfBranches = input.nextInt();
        input.nextLine();

        for (int i=0;i<numberOfBranches;i++){
            int start = input.nextInt();
            int end = input.nextInt();
            input.nextLine();
            connections.get(start).add(end);
            connections.get(end).add(start);
        }

        int numberOfCases = input.nextInt();
        input.nextLine();

        int[] whereFamilyStanding = new int[numberOfCases];
        for (int i=0;i<numberOfCases;i++){
            whereFamilyStanding[i] = input.nextInt();
        }
        input.nextLine();

        for (int currentFamily : whereFamilyStanding){
            if (numberOfPumpkins[currentFamily-1] > 0){
                System.out.println(currentFamily);
                continue;
            }
            else{
                boolean [] visited = new boolean[numberOfSupermarkets+1];
                int[] supermarket = findSupermarket(currentFamily, numberOfPumpkins, connections, visited);
                System.out.println(supermarket[0] == Integer.MAX_VALUE ? "Impossivel" : supermarket[0]);
            }
        }
    }

    private static int[] findSupermarket(int start, int[] numberOfPumpkins,
                                       HashMap<Integer, HashSet<Integer>> connections, boolean[] visited){
        int[] bestSupermarket = {Integer.MAX_VALUE, -1};
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // Check if this supermarket has pumpkins
            if (numberOfPumpkins[current-1] > 0) {
                if (numberOfPumpkins[current-1] > bestSupermarket[1]) {
                    bestSupermarket[0] = current;
                    bestSupermarket[1] = numberOfPumpkins[current-1];
                } else if (numberOfPumpkins[current-1] == bestSupermarket[1]) {
                    bestSupermarket[0] = Math.min(bestSupermarket[0], current);
                }
            }

            // Explore neighbors
            for (Integer neighbor : connections.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }

        return bestSupermarket;
    }

}