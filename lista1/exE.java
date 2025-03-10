import java.util.*;

class exE {

    private static final Scanner input = new Scanner(System.in);
    private static int currentHour;
    private static int currentMinute;

    private static void calculatesTimeSpent (int min){
        currentMinute += min;
        if (currentMinute >= 60) {
            currentHour++;
            currentMinute -= 60;
        }
    }

    public static void main(String[] args) {
        Queue<Integer> line = new ArrayDeque<>();
        currentHour = 9;
        currentMinute = 0;
        int lost = 0;
        int numberOfClients = input.nextInt();
        input.nextLine();
        int i =0;
        while (i < numberOfClients) {

            List<Integer> client = new ArrayList<>();
            for (int j=0;j<3;j++) client.add(input.nextInt());
            input.nextLine();
            int hourArrived = client.get(0);
            int minuteArrived = client.get(1);
            int timeSpent = client.get(2);

            if ((hourArrived > 12 && hourArrived < 15)
                    || hourArrived < 9
                    || hourArrived > 19
                    || ((hourArrived==12||hourArrived==19) && minuteArrived != 0)){
                lost++;
                i++;
                continue;
            }

            while (((hourArrived > currentHour) || (hourArrived == currentHour && minuteArrived >= currentMinute))
                    && !line.isEmpty()) {
                calculatesTimeSpent(line.remove());
            }

            if ((hourArrived < currentHour) || (hourArrived == currentHour && minuteArrived <= currentMinute)){
                if (line.size() == 3) lost++;
                else line.add(Math.min(40,timeSpent));
            }
            else {
                if (hourArrived > currentHour){
                    currentHour = hourArrived;
                    currentMinute = minuteArrived;
                }
                else if (hourArrived == currentHour){
                    currentMinute = Math.max(minuteArrived, currentMinute);
                }
                calculatesTimeSpent(Math.min(40, timeSpent));
            }
            i++;
        }
        System.out.println("Perdeu = " + lost);
    }
}