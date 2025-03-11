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
                    HashSet<Integer> singleDigit = new HashSet<>();
                    singleDigit.add(currentReview -'0');
                    setsArray.set(j,singleDigit);
                }
            }
        }

        for (int i=0;i<numberSize;i++) {
            if (confirmedNumbers.size() == numberSize && setsArray.get(i).size()>1) {
                setsArray.get(i).removeAll(inPosition);
            }
            setsArray.get(i).removeIf(n -> !confirmedNumbers.contains(n));
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
}