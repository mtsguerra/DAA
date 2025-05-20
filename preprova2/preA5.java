import java.util.*;

class edededg{
    int from;
    int to;
    int places;
    public edededg(int from, int to, int places){
        this.from = from;
        this.to = to;
        this.places = places;
    }
}

public class preA5 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int minElements = input.nextInt();
        int origin = input.nextInt();
        int maxElements = input.nextInt();

        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<edededg> [] graph = new ArrayList[nNodes];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }
    }

}
