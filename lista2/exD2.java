import java.util.*;

class exD2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfNodes = input.nextInt();
        int numberOfConnections = input.nextInt();

        HashMap<Integer, HashSet<Integer>> branches = new HashMap<>();
        for (int i = 1; i <= numberOfNodes; i++) {
            branches.put(i, new HashSet<>());
        }

        for (int i = 0; i < numberOfConnections; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            branches.get(node1).add(node2);
            branches.get(node2).add(node1);
        }

        int[] biggestOne = precomputeLargestReachable(numberOfNodes, branches);

        int numberOfQuestions = input.nextInt();
        for (int i = 0; i < numberOfQuestions; i++) {
            int start = input.nextInt();
            if (branches.get(start).isEmpty()) {
                System.out.println("No " + start + ": " + start);
                continue;
            }
            System.out.println("No " + start + ": " + biggestOne[start]);
        }
    }

    private static int[] precomputeLargestReachable(int numberOfNodes,
                                                                        HashMap<Integer, HashSet<Integer>> neighbors) {
        int[] largestReachable = new int[numberOfNodes+1];
        boolean[] visited = new boolean[numberOfNodes + 1];

        for (int i = 1; i <= numberOfNodes; i++) {
            largestReachable[i] = i;
        }

        for (int start = 1; start <= numberOfNodes; start++) {
            // finds the cycle for each node
            if (!visited[start]) {
                List<Integer> cycle = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(start);
                visited[start] = true;
                cycle.add(start);

                while (!queue.isEmpty()) {
                    int current = queue.poll();
                    for (int neighbor : neighbors.get(current)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                            cycle.add(neighbor);
                        }
                    }
                }
                int biggestInTheCycle = Collections.max(cycle);
                for (int node : cycle) {
                    largestReachable[node] = biggestInTheCycle;
                }
            }
        }
        return largestReachable;
    }
}