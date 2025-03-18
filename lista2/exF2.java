import java.util.*;

class exF2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfCases = input.nextInt();
        input.nextLine();
        for (int i=0;i<numberOfCases;i++){
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

        }
        int z=1;
        z*=2;
    }

    private static int[] findGroups (int numberOfStudents, HashMap<Integer, HashSet<Integer>> connections){
        // int [groups4+, inGroup]
        int[] resp = new int[2];

        return resp;
        }

    }

}