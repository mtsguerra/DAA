import java.util.*;

class exG{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int numberSize =  input.nextInt();
        int totalTries = input.nextInt();
        input.nextLine();

        Set<Integer> confirmedNumbers = new HashSet<>();
        Set<Integer> inPosition = new HashSet<>();
        int totalRights = 0;

        List<HashSet<Integer>> setsArray = new ArrayList<>();
        for (int i = 0; i < numberSize; i++) {
            setsArray.add(new HashSet<>());
            for (int j = 0; j < 10; j++) {
                setsArray.get(i).add(j);
            }
        }
        for (int i=0;i<totalTries;i++){
            String numberString = input.nextLine();
            String review = input.nextLine();
            for (int j=0;j<numberSize;j++){
                char currentReview = review.charAt(j);
                char currentDigit = numberString.charAt(j);
                int numNow = currentDigit - '0';
                if (currentReview == 'X'){;
                    for (int k=0;k<numberSize;k++){
                        setsArray.get(k).remove(numNow);
                    }
                }
                else if (currentReview == '-'){
                    confirmedNumbers.add(numNow);
                    setsArray.get(j).remove(numNow);
                }
                else if (Character.isDigit(currentReview)){
                    confirmedNumbers.add(numNow);
                    inPosition.add(numNow);
                    if (setsArray.get(j).size()!=1) totalRights++;
                    HashSet<Integer> singleDigit = new HashSet<>();
                    singleDigit.add(currentReview -'0');
                    setsArray.set(j,singleDigit);
                }
            }
        }

        if (confirmedNumbers.size() == numberSize) {
            for (int i=0;i<numberSize;i++){
                setsArray.get(i).removeIf(n->!confirmedNumbers.contains(n));
                if (setsArray.get(i).size() == 1) continue;
                setsArray.get(i).removeAll(inPosition);
            }
        }

        else {
            int[] countOfEach = counts(setsArray);
            while (true){
                for (int i=0;i<10;i++) {
                    int count = countOfEach[i];
                    int number = i;
                    if (count == 1){
                        for (int j=0;j<numberSize;j++){
                            if (setsArray.get(j).size() > 1 && setsArray.get(j).contains(number)){
                                setsArray.set(j,new HashSet<>(Set.of(number)));
                            }
                        }
                    }
                }
                int[] newCount = counts(setsArray);
                if (Arrays.equals(countOfEach, newCount)) break;
                countOfEach = newCount;
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean found = true;
        for (int i=0;i<numberSize;i++){
            if (setsArray.get(i).size() == 1) {
                sb.append(setsArray.get(i).iterator().next());
            }
            else {
                System.out.print("AINDA NAO SEI");
                found = false;
                break;
            }
        }
        if (found){
            System.out.println("RESPOSTA " + sb.toString());
        }
    }

    private static int[] counts (List<HashSet<Integer>> setsArray){
        int[] countOfEach = new int[10];
        setsArray.stream().filter(possibles -> possibles.size() > 1).forEach(possibles ->
        {
            for (int possible : possibles) {
                countOfEach[possible]++;
            }
        });
        return countOfEach;
    }
}