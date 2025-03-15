import java.util.*;

class exD {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int stadingPeople = 0;
        int avaiableSeats = 0;

        int numberOfChairs = input.nextInt();
        input.nextLine();

        HashMap<Integer, Integer> chairs = new HashMap<>();
        for (int i=0;i<numberOfChairs;i++){
            int typeOfChair = input.nextInt();
            input.nextLine();
            int numberOfThisType = input.nextInt();
            input.nextLine();
            if (numberOfThisType>=1){
                chairs.put(typeOfChair,numberOfThisType);
                avaiableSeats+=numberOfThisType;
            }
        }

        int numberOfPeople = input.nextInt();
        input.nextLine();

        Queue<List<Integer>> people = new LinkedList<>();
        for (int i=0;i<numberOfPeople;i++){
            int numberOfOptions = input.nextInt();
            input.nextLine();
            List<Integer> crr = new ArrayList<>();
            for (int j=0;j<numberOfOptions;j++){
                crr.add(input.nextInt());
                input.nextLine();
            }
            if (crr.isEmpty()) stadingPeople++;
            else{
                people.add(crr);
            }
        }

        while (!people.isEmpty()){
            List<Integer> currentPref = people.poll();
            boolean seated = false;
            for (int n : currentPref){
                if (chairs.containsKey(n)){
                    int remaining = chairs.get(n)-1;
                    if (remaining == 0) chairs.remove(n);
                    else chairs.put(n,remaining);
                    seated = true;
                    avaiableSeats--;
                    break;
                }
            }
            if (!seated) stadingPeople++;
        }

        System.out.println(stadingPeople);
        System.out.println(avaiableSeats);
    }
}