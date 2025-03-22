import java.util.*;

class exCsecondTry{

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        boolean[] presents = new boolean[10001];
        int n = input.nextInt();
        while (n!=0){
            if (presents[n]){
                while (stack.peek()!=n){
                    presents[stack.pop()] = false;
                }
            }
            else {
                presents[n] = true;
                stack.push(n);
            }
            n=input.nextInt();
        }
        Stack<Integer> stack2 = new Stack<>();
        while (!stack.isEmpty()){
            stack2.add(stack.pop());
        }
        while (!stack2.isEmpty()){
            System.out.println(stack2.pop());
        }
    }
}