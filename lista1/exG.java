import java.util.*;

class exG{

    private static final Scanner input = new Scanner(System.in);

    // because the number of digits have a maximum of 5, I thought of using an array with all the possible
    // numbers with n digits, I think it would work for this problem, but I tried to do a code that would work for n>5
    public static void main(String[] args) {

        int numberSize =  input.nextInt();
        int totalTries = input.nextInt();
        if (input.hasNext()) input.nextLine();

        // set to keep track of the confirmed digits in the number that are out of position
        Set<Integer> confirmedNumbersOutOfPosition = new HashSet<>();
        // set to keep track of the digits that are in the correct position
        Set<Integer> numbersInPosition = new HashSet<>();

        // list of sets to keep track of the possible digits for each position
        List<HashSet<Integer>> setsArray = new ArrayList<>();
        for (int i = 0; i < numberSize; i++) {
            setsArray.add(new HashSet<>());
            for (int j = 0; j < 10; j++) {
                setsArray.get(i).add(j);
            }
        }

        for (int i=0;i<totalTries;i++){
            String guess = input.nextLine();
            String review = input.nextLine();

            // iterates through the digits of this try and review
            for (int j=0;j<numberSize;j++){
                int numNow = guess.charAt(j) - '0';
                char currentReview = review.charAt(j);

                // indicates that this digit do not belong to the number
                if (currentReview == 'X'){;
                    // remove from all the sets
                    for (int k=0;k<numberSize;k++){
                        setsArray.get(k).remove(numNow);
                    }
                }
                // indicates that this digit is in the number and in the correct position
                else if (Character.isDigit(currentReview)){
                    // sets the set in this position to only contain this digit
                    int tmp = currentReview - '0';
                    HashSet<Integer> singleDigit = new HashSet<>();
                    singleDigit.add(tmp);
                    numbersInPosition.add(tmp);
                    setsArray.set(j,singleDigit);
                }
                // indicates that this digit is in the number, but in another position
                // so, I can remove it from this current position
                else{
                    setsArray.get(j).remove(numNow);
                    confirmedNumbersOutOfPosition.add(numNow);
                }
            }
        }
        // remove the confirmed digits in position from the confirmed digits out of position
        HashSet<Integer> confirmedWithoutInPositionOnes = new HashSet<>(confirmedNumbersOutOfPosition);
        confirmedWithoutInPositionOnes.removeAll(numbersInPosition);

        // count the occurrences of the numbers out of position
        int[] countOfEach = counts(setsArray);
        // tries to filter the number in order of the confirmed digits out of position
        // logic similar to sudoku///inspired by
        while (true){
            // found the number
            if (Arrays.equals(countOfEach,new int[10])) break;
            for (int i=0;i<10;i++) {
                // iterate through the count of each number
                int count = countOfEach[i];
                // it appears only once and is a confirmed number out of position
                if (count == 1 && confirmedWithoutInPositionOnes.contains(i)) {
                    confirmedWithoutInPositionOnes.remove(i);
                    for (int j=0;j<numberSize;j++){
                        // sets the set in this position to only contain this digit
                        if (setsArray.get(j).size() > 1 && setsArray.get(j).contains(i)){
                            HashSet<Integer> singleDigit = new HashSet<>();
                            singleDigit.add(i);
                            setsArray.set(j,singleDigit);
                            break;
                        }
                    }
                }
            }
            int[] newCount = counts(setsArray);
            // no changes were made
            if (Arrays.equals(countOfEach, newCount)) break;
            countOfEach = newCount;
        }

        // generate the answer
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<numberSize;i++){
            if (setsArray.get(i).size() == 1) {
                sb.append(setsArray.get(i).iterator().next());
            }
            else {
                System.out.println("AINDA NAO SEI");
                return;
            }
        }
        System.out.println("RESPOSTA " + sb);
    }

    private static int[] counts (List<HashSet<Integer>> setsArray){
        int[] countOfEach = new int[10];
        // filters the sets that have more than one possible digit and counts the occurrences of each digit
        setsArray.stream().filter(possibles -> possibles.size() > 1).forEach(possibles ->
        {
            for (int possible : possibles) {
                countOfEach[possible]++;
            }
        });
        return countOfEach;
    }
}