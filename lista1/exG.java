import java.util.Scanner;

class exG{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int sizeNumber =  input.nextInt();
        input.nextLine();
        int totalTries = input.nextInt();
        input.nextLine();

        String confirmed = "X".repeat(sizeNumber);
        int[] numbersWeKnow =  new int[sizeNumber];

        for (int i=0;i<totalTries*2;i++){
            String currentTry = input.nextLine();
            String ans = input.nextLine();
            for (int j=0;j<ans.length();j++){
                char ch = ans.charAt(j);
                if (ch=='X') continue;
                //else if (ch=='-')
            }
        }
    }
}