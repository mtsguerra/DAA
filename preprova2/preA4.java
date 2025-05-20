import java.util.*;

class edd{
    int from;
    int to;
    int temperature;
    int price;
    public edd(int from, int to, int temperature, int price){
        this.from = from;
        this.to = to;
        this.temperature = temperature;
        this.price = price;
    }
}

public class preA4 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nEdges = input.nextInt();

        @SuppressWarnings("unchecked")
        List<edd> [] graph = new ArrayList[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < nEdges; i++) {
            int node1 = input.nextInt();
            int node2 = input.nextInt();
            int temperature = input.nextInt();
            int price = input.nextInt();
            graph[node1].add(new edd(node1, node2, temperature, price));
            graph[node2].add(new edd(node2, node1, temperature, price));
        }

        while (true) {
            int sizePath = input.nextInt();
            if (sizePath == 0) break;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < sizePath; i++) {
                queue.add(input.nextInt());
            }
            int coldest = Integer.MAX_VALUE;
            int hottest = Integer.MIN_VALUE;
            int inital = queue.poll();
            while (!queue.isEmpty()) {
                int next = queue.poll();
                for (edd e : graph[inital]) {
                    if (e.to!=next) continue;
                    coldest = Math.min(coldest, e.temperature);
                    hottest = Math.max(hottest, e.temperature);
                }
                inital = next;
            }
            System.out.println(coldest + " " + hottest);
        }

    }

}
