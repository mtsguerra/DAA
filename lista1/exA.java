import java.util.*;

class exA {

    private static final Scanner input = new Scanner(System.in);

    /**
     * This problem revolves around trying to find closed cycles in the graph and return them, as well as
     * the number of people that do not belong in any group.
     * This method works as helper to find the cycles in the graph.
     * @param start Starting node.
     * @param a The array to be checked.
     * @param visited Boolean array to keep track of the visited nodes.
     * @return The cycle found in the array, if found.
     */
    private static List<Integer> findCycle (int start, int[] a, boolean[] visited){
        List<Integer> cycle = new ArrayList<>();
        int current = start;
        while (!visited[current]){
            visited[current] = true;
            cycle.add(current);
            current = a[current];
            // closes the cycle
            if (current == start){
                return cycle;
            }
            // creates a newCycle to readjust the current cycle if necessary
            if (visited[current] && cycle.contains(current)){
                List<Integer> newCycle = new ArrayList<>();
                int temp = current;
                do {
                    newCycle.add(temp);
                    temp =  a[temp];
                } while (temp!=current);
                return newCycle;
            }
        }
        // not found a cycle
        List<Integer> cycleNot = new ArrayList<>();
        cycleNot.add(start);
        return cycleNot;
    }

    public static void main(String[] args) {

        int n = input.nextInt();
        input.nextLine();
        int[] a  = new int[n+1];
        int inGroup = 0;

        for (int i=1;i<=n;i++){
            a[i] = input.nextInt();
            input.nextLine();
        }

        boolean[] visited =  new boolean[n+1];
        List<List<Integer>> groups = new ArrayList<>();

        for (int i=1;i<=n;i++){
            if (!visited[i]){
                List<Integer> group = findCycle(i,a,visited);
                if (group.size() >= 3){
                    groups.add(group);
                }
            }
        }
        if (groups.isEmpty()){
            System.out.println(n);
            return;
        }
        groups.sort((group1, group2) -> Integer.compare(group1.get(0), group2.get(0)));

        for (List<Integer> g : groups){
            System.out.print(g.size()+" ");
            inGroup+=g.size();
            int maxIndex = g.indexOf(Collections.max(g));
            for (int i=maxIndex;i<g.size();i++){
                System.out.print(g.get(i)+" ");
            }
            if (maxIndex!=0){
                for (int i=0;i<maxIndex-1;i++){
                    System.out.print(g.get(i)+" ");
                }
                System.out.print(g.get(maxIndex-1));
            }
            System.out.println();
        }
        System.out.println(n- inGroup);
    }
}