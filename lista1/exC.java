import java.util.HashSet;
import java.util.Scanner;
import java.util.Stack;

class exC {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        boolean[] allPlaces = new boolean[10001];
        Stack<Integer> stack = new Stack<>();
        int start = input.nextInt();
        input.nextLine();
        stack.push(start);
        allPlaces[start] = true;
        int n=1;
        while (true) {
            n = input.nextInt();
            input.nextLine();
            if (n==0) break;
            if (allPlaces[n]) {
                while (stack.peek()!=n){
                    allPlaces[stack.pop()] = false;
                }
            }
            else {
                allPlaces[n] = true;
                stack.push(n);
            }
        }
        for (int num : stack){
            System.out.println(num);
        }
    }
}