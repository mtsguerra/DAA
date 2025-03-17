import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

class exA2 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfPaths = input.nextInt();
        int numberOfWays = input.nextInt();
        input.nextLine();

        HashMap<Integer,HashSet<Integer>> map = new HashMap<>();
        for (int i = 1; i <= numberOfPaths; i++) {
            map.put(i,new HashSet<>());
        }
        for (int i=0;i<numberOfWays;i++){
            int pathSize = input.nextInt();
            int current = input.nextInt();
            for (int j=0;j<pathSize-1;j++){
                int next = input.nextInt();
                map.get(current).add(next);
                current=next;
            }
            input.nextLine();
        }

        for (HashSet<Integer> set : map.values()){
            System.out.println(set.size());
        }

    }
}