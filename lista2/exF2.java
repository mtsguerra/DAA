import java.util.*;

class exF2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfCases = input.nextInt();
        input.nextLine();
        for (int i=1;i<=numberOfCases;i++){
            int numberOfStudents = input.nextInt();
            HashMap<Integer, HashSet<Integer>> connections = new HashMap<>();
            for (int k =1;k<=numberOfStudents;k++){
                connections.put(k,new HashSet<>());
            }
            for (int j=0;j<numberOfStudents;j++){
                int currentStudent = input.nextInt();
                int numberOfBetterRelations = input.nextInt();
                for (int m=0;m<numberOfBetterRelations;m++){
                    int btRelation = input.nextInt();
                    connections.get(currentStudent).add(btRelation);
                }
            }
            int[] groups = findGroups(numberOfStudents,connections);
            System.out.println("Caso #" + i);
            System.out.println(groups[0] + " " + groups[1]);
        }
    }

    private static int[] findGroups (int numberOfStudents, HashMap<Integer, HashSet<Integer>> connections){
        List<HashSet<Integer>> sccs = findStronglyConnectedComponents(numberOfStudents, connections);
        int largeGroups = 0;
        int studentsInSmallGroups = 0;
        for (HashSet<Integer> scc : sccs){
            if (scc.size() >= 4){
                largeGroups++;
            }
            else{
                studentsInSmallGroups += scc.size();
            }
        }
        return new int[] {largeGroups, studentsInSmallGroups};
    }

    private static List<HashSet<Integer>> findStronglyConnectedComponents(int numberOfStudents,
                                                                          HashMap<Integer, HashSet<Integer>> connections){
        // kosaraju's algorithm
        Stack<Integer> stack = new Stack<>();
        boolean visited[] = new boolean[numberOfStudents+1];
        for (int i=1;i<=numberOfStudents;i++){
            if (!visited[i]){
                dfs(i, connections,visited,stack);
            }
        }
        HashMap<Integer, HashSet<Integer>> reversedGraph = reverseGraph(connections);
        Arrays.fill(visited, false);
        List<HashSet<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()){
            int current = stack.pop();
            if (!visited[current]){
                HashSet<Integer> scc = new HashSet<>();
                dfsSCC(current, reversedGraph, visited, scc);
                sccs.add(scc);
            }
        }
        return sccs;
    }

    private static void dfs(int node, HashMap<Integer, HashSet<Integer>> connections,
                            boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for (int neighbor : connections.get(node)) {
            if (!visited[neighbor]) {
                dfs(neighbor, connections, visited, stack);
            }
        }
        stack.push(node);
    }

    private static void dfsSCC(int node, HashMap<Integer, HashSet<Integer>> connections,
                               boolean[] visited, HashSet<Integer> scc) {
        visited[node] = true;
        scc.add(node);
        for (int neighbor : connections.get(node)) {
            if (!visited[neighbor]) {
                dfsSCC(neighbor, connections, visited, scc);
            }
        }
    }

    private static HashMap<Integer, HashSet<Integer>> reverseGraph(HashMap<Integer,
            HashSet<Integer>> connections) {

        HashMap<Integer, HashSet<Integer>> reversed = new HashMap<>();
        for (int node : connections.keySet()) {
            reversed.put(node, new HashSet<>());
        }
        for (int node : connections.keySet()) {
            for (int neighbor : connections.get(node)) {
                reversed.get(neighbor).add(node);
            }
        }
        return reversed;
    }

}