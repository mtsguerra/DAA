import java.util.*;

class exAsecondTry {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
            int nPeople = input.nextInt();
            HashMap<Integer, Integer> branches = new HashMap<>();
            for (int i = 1; i <= nPeople; i++) {
                branches.put((i),(input.nextInt()));
            }
            int inGroups = 0;
            boolean[] visited = new boolean[nPeople+1];
            // group, index of the biggest element
            List<List<Integer>> groups = new ArrayList<>();
            List<Integer> biggestIndexes = new ArrayList<>();
            for (int i=1;i<=nPeople;i++){
                if (!visited[i]){
                    Queue<Integer> queue = new LinkedList<>();
                    List<Integer> group = new ArrayList<>();
                    int[] biggest = new int[2];
                    group.add(i);
                    biggest[0]=i;
                    biggest[1]=0;
                    queue.add(i);
                    visited[i] = true;
                    while (!queue.isEmpty()){
                        int current = queue.poll();
                        int next = branches.get(current);
                        if (!visited[next]){
                            if (next > biggest[0]){
                                biggest[0]=next;
                                biggest[1]=group.size();
                            }
                            group.add(next);
                            queue.add(next);
                            visited[next] = true;
                        }
                    }
                    if (group.size()>=3){
                        inGroups+=group.size();
                        groups.add(group);
                        biggestIndexes.add(biggest[1]);
                    }
                }
            }
            for (int j=0;j<groups.size();j++){
                List<Integer> currentGroup = groups.get(j);
                int biggestIndex = biggestIndexes.get(j);
                System.out.print(currentGroup.size()+" ");
                for (int i= biggestIndex;i<=currentGroup.size()-1;i++){
                    System.out.print(currentGroup.get(i)+" ");
                }
                if (biggestIndex!=0){
                    for (int i=0;i<biggestIndex-1;i++){
                        System.out.print(currentGroup.get(i)+" ");
                    }
                    System.out.println(currentGroup.get(biggestIndex-1));
                }
            }
            System.out.println(nPeople-inGroups);
    }
}