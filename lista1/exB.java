import java.util.HashSet;
import java.util.Scanner;

class exB{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int numberOfRoutes = input.nextInt();
        int numberOfElementsInTheGroup = input.nextInt();
        int originalCity = input.nextInt();
        int finalCity = input.nextInt();

        input.nextLine();

        int group = 0;
        int totalObstacles = Integer.MAX_VALUE;
        int totalSeats = 0;

        for (int i=0;i<numberOfRoutes;i++){
            int numberOfStops = input.nextInt();
            input.nextLine();

            boolean isStartPresent = false;
            boolean isEndPresent = false;
            boolean isFirst = false;

            int currentObstacles = 0;
            int currentSeats = Integer.MAX_VALUE;
            HashSet<Integer> stops = new HashSet<>();
            for (int j=0;j<numberOfStops-1;j++){
                int start = input.nextInt();
                if (start==originalCity){
                    isStartPresent = true;
                    if (!stops.contains(finalCity)) isFirst = true;
                }
                if (start==finalCity) isEndPresent = true;
                if (input.nextInt() == 1 && !isEndPresent) currentObstacles++;
                int availableSeats = input.nextInt();
                stops.add(start);
                if (isStartPresent && !isEndPresent){
                    currentSeats = Math.min(currentSeats,availableSeats);
                }
            }
            int lastStop = input.nextInt();
            input.nextLine();
            if (lastStop==finalCity) isEndPresent = true;
            if (isStartPresent && isEndPresent && isFirst && currentSeats >= numberOfElementsInTheGroup){
                if (currentObstacles < totalObstacles) {
                    group = i + 1;
                    totalObstacles = currentObstacles;
                    totalSeats = currentSeats;
                }
                else if (currentObstacles == totalObstacles && currentSeats > totalSeats){
                    group = i + 1;
                    totalSeats = currentSeats;
                }
            }
        }
        if (group==0) System.out.println("Impossivel");
        else{
            System.out.println("Rota = " + group+" Probs = " + totalObstacles + " Lugares = " + totalSeats);
        }
    }
}