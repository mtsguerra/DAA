import java.util.*;

public class exC5 {

    static class Aresta {
        int destino;
        int alturaMax, larguraMax;

        Aresta(int destino, int alturaMax, int larguraMax) {
            this.destino = destino;
            this.alturaMax = alturaMax;
            this.larguraMax = larguraMax;
        }

        int getRestricao() {
            if (alturaMax == -1 && larguraMax == -1) return Integer.MAX_VALUE;
            if (alturaMax == -1) return larguraMax;
            if (larguraMax == -1) return alturaMax;
            return Math.min(alturaMax, larguraMax);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int origem = sc.nextInt();
        int minDim = sc.nextInt();
        int maxDim = sc.nextInt();

        int n = sc.nextInt();
        int r = sc.nextInt();

        // Use Map<Integer, Map<Integer, Aresta>> for efficient edge updates
        Map<Integer, Map<Integer, Aresta>> grafo = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            grafo.put(i, new HashMap<>());
        }

        // Read edges
        for (int i = 0; i < r; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            grafo.get(a).put(b, new Aresta(b, -1, -1));
            grafo.get(b).put(a, new Aresta(a, -1, -1));
        }

        int restricoes = sc.nextInt();

        // Apply restrictions efficiently using direct map access
        for (int i = 0; i < restricoes; i++) {
            int u = sc.nextInt(), v = sc.nextInt();
            int altura = sc.nextInt(), largura = sc.nextInt();
            grafo.get(u).put(v, new Aresta(v, altura, largura));
            grafo.get(v).put(u, new Aresta(u, altura, largura));
        }

        int[] maxDimensao = new int[n + 1];
        Arrays.fill(maxDimensao, 0);
        maxDimensao[origem] = maxDim;

        PriorityQueue<int[]> fila = new PriorityQueue<>((a, b) -> b[1] - a[1]); // [node, capacity]
        fila.add(new int[]{origem, maxDim});

        while (!fila.isEmpty()) {
            int[] atual = fila.poll();
            int noAtual = atual[0];
            int capacidadeAtual = atual[1];

            if (capacidadeAtual < maxDimensao[noAtual]) continue;

            // Iterate over edges with efficient map values()
            for (Aresta aresta : grafo.get(noAtual).values()) {
                int proximo = aresta.destino;
                int restricao = aresta.getRestricao();
                int novaCapacidade = Math.min(capacidadeAtual, restricao);

                if (novaCapacidade > maxDimensao[proximo] && novaCapacidade >= minDim) {
                    maxDimensao[proximo] = novaCapacidade;
                    fila.add(new int[]{proximo, novaCapacidade});
                }
            }
        }

        List<String> output = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i == origem) continue;
            if (maxDimensao[i] == 0 || maxDimensao[i] < maxDim) {
                output.add("No " + i + ": " + (maxDimensao[i] >= minDim ? maxDimensao[i] : 0));
            }
        }

        if (output.isEmpty()) {
            System.out.println("Ok todos destinos!");
        } else {
            for (String linha : output) {
                System.out.println(linha);
            }
        }
    }
}
