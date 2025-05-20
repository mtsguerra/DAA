import java.util.*;

class edgs{
    int from;
    int to;
    int weight;
    public edgs(int from, int to, int weight){
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class preE3 {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nNodes = input.nextInt();
        int nEdges = input.nextInt();
        @SuppressWarnings("unchecked")

        List<edgs> [] graph = new ArrayList[nNodes+1];
        List<edgs> allEdges = new ArrayList<>();
        int[] earliestStart = new int[nNodes+1];
        int[] deriveCount = new int[nNodes+1];

        for (int i = 1; i <= nNodes; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i=0;i<nEdges;i++) {
            int from = input.nextInt();
            int to = input.nextInt();
            int weight = input.nextInt();
            graph[from].add(new edgs(from, to, weight));
            allEdges.add(new edgs(from, to, weight));
            deriveCount[to]++;
        }

        // Kahn's
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= nNodes; i++) {
            if (deriveCount[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int current = q.poll();
            for (edgs edge : graph[current]) {
                int to = edge.to;
                int weight = edge.weight;
                earliestStart[to] = Math.max(earliestStart[current] + weight, earliestStart[to]);
                deriveCount[to]--;
                if (deriveCount[to] == 0) {
                    q.add(to);
                }
            }
        }

        // Calc end of each actv
        int[] allEnds = new int[nEdges];
        int p=0;
        for (edgs edge : allEdges) {
            int end = edge.weight + earliestStart[edge.from];
            allEnds[p++] = end;
        }
        Arrays.sort(allEnds);

        int nQuestions = input.nextInt();
        for (int i=0;i<nQuestions;i++){
            int maxTime = input.nextInt();
            int count = 0;
            for (int n : allEnds) {
                if (n <= maxTime) count++;
                else break;
            }
            System.out.println(maxTime + " "+count);
        }
    }
}
