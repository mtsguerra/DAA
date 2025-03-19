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

    // StronglyConnectedComponents SCC

    private static HashMap<Integer, HashSet<Integer>> reverseGraph(HashMap<Integer, HashSet<Integer>> original) {
        HashMap<Integer, HashSet<Integer>> result = new HashMap<>();
        for (int i : original.keySet()){
            result.put(i,new HashSet<>());
        }
        for (int i : original.keySet()){
            for (int j : original.get(i)){
                result.get(j).add(i);
            }
        }
        return result;
    }


    private static int[] findGroups(int numberOfStudents, HashMap<Integer, HashSet<Integer>> connections){
        // int [groups4+, notInGroups4+]
        int[] resp = new int[2];
        resp[1] = numberOfStudents;
        boolean[] visited = new boolean[numberOfStudents+1];

        // remove all students that can not form a connection
        while (true){
            HashMap<Integer, HashSet<Integer>> og = new HashMap<>();
            for (Map.Entry<Integer, HashSet<Integer>> entry : connections.entrySet()) {
                og.put(entry.getKey(), new HashSet<>(entry.getValue()));
            }
            HashSet<Integer> toEliminate = new HashSet<>();
            for (int i=1;i<=numberOfStudents;i++){
                if (connections.get(i).isEmpty()) toEliminate.add(i);
            }
            for (int i : connections.keySet()){
                connections.get(i).removeAll(toEliminate);
            }
            if (og.equals(connections)) break;
        }

        HashMap<Integer, HashSet<Integer>> reversed = reverseGraph(connections);
        for (int i=1;i<=numberOfStudents;i++){
            if (!visited[i]){
                HashSet<Integer> group = new HashSet<>();
                dfs(i,reversed,visited, group, new ArrayList<>());
                if (group.size() >= 4){
                    resp[0]++;
                    resp[1] -= group.size();
                }
            }
        }
        return resp;
    }
    // performs the dfs to form a valid group
    // revolves around in the idea that if in the dfs that is being performed the leaf node
    // is going to be or a visited node ( forming a cycle ) or a node that is not in the group
    private static void dfs(int start, HashMap<Integer, HashSet<Integer>> reversed,
                            boolean[] visited, HashSet<Integer> group, List<Integer> currentGroup){
        HashSet<Integer> ways = reversed.get(start);
        if (ways.isEmpty()){
            return;
        }
        visited[start] = true;
        currentGroup.add(start);
        for (int i : ways){
            if (!visited[i]){
                dfs(i, reversed,visited, group, currentGroup);
            }
            else {
                group.addAll(currentGroup);
            }
        }
    }
}