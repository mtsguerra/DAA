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
        int[] visited = new int[numberOfStudents+1];

        while (true){
            HashMap<Integer, HashSet<Integer>> og = new HashMap<>(connections);
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
            if (visited[i]==0){
                HashSet<Integer> group = new HashSet<>();
                dfs(i,reversed,visited, group, new ArrayList<>());
                boolean valid = true;
                for (int j : group){
                    boolean found = false;
                    for (int k : connections.get(j)){
                        if (group.contains(k)){
                            found = true;
                            break;
                        }
                    }
                    if (!found){
                        for (int k : group){
                            visited[k] = 0;
                        }
                        visited[j] = 1;
                        valid = false;
                        break;
                    }
                }
                if (group.size() >= 4 && valid){
                    resp[0]++;
                    resp[1] -= group.size();
                }
            }
        }
        return resp;
    }
    private static void dfs(int start, HashMap<Integer, HashSet<Integer>> reversed,
                            int[] visited, HashSet<Integer> group, List<Integer> currentGroup){
        HashSet<Integer> ways = reversed.get(start);
        if (ways.isEmpty()){
            return;
        }
        visited[start] = 1;
        currentGroup.add(start);
        for (int i : ways){
            if (visited[i] == 0){
                dfs(i, reversed,visited, group, currentGroup);
            }
            else if (visited[i] == 1) {
                group.addAll(currentGroup);
            }
        }
    }
}