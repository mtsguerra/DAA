import java.util.*;

class exG{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int numberSize =  input.nextInt();
        int totalTries = input.nextInt();
        input.nextLine();

        Set<Integer> confirmedNumbers = new HashSet<>();
        Set<Integer> inPosition = new HashSet<>();

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
                char currentDigit = numberString.charAt(j);
                char currentReview = review.charAt(j);
                int numNow = currentDigit - '0';
                if (currentReview == 'X'){;
                    for (int k=0;k<numberSize;k++){
                        setsArray.get(k).remove(numNow);
                    }
                }
                else if (currentReview == '-'){
                    setsArray.get(j).remove(numNow);
                    confirmedNumbers.add(numNow);
                }
                else if (Character.isDigit(currentReview)){
                    HashSet<Integer> singleDigit = new HashSet<>();
                    singleDigit.add(currentReview -'0');
                    inPosition.add(numNow);
                    setsArray.set(j,singleDigit);
                }
            }
        }

        if (confirmedNumbers.size() == numberSize) {
            for (int i=0;i<numberSize;i++){
                setsArray.get(i).removeIf(num->!confirmedNumbers.contains(num));
                if (setsArray.get(i).size() == 1) continue;
                setsArray.get(i).removeAll(inPosition);
            }
        }

        else {
            int[] countOfEach = counts(setsArray);
            while (true){
                for (int i=0;i<10;i++) {
                    int count = countOfEach[i];
                    if (count == 1 && confirmedNumbers.contains(i)) {
                        confirmedNumbers.remove(i);
                        for (int j=0;j<numberSize;j++){
                            if (setsArray.get(j).size() > 1 && setsArray.get(j).contains(i)){
                                HashSet<Integer> singleDigit = new HashSet<>();
                                singleDigit.add(i);
                                setsArray.set(j,singleDigit);
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
                System.out.println("AINDA NAO SEI");
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