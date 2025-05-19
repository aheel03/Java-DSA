import java.util.*;

public class JohnsonAlgorithm {
    static final int INF = Integer.MAX_VALUE / 2;
    static int V;
    static List<int[]>[] adj;
    static List<int[]> edges = new ArrayList<>();

    public static void addEdge(int u, int v, int w) {
        adj[u].add(new int[]{v, w});
        edges.add(new int[]{u, v, w});
    }

    public static int[] bellmanFord(int source) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, INF);
        dist[source] = 0;

        for (int i = 0; i < V; i++) {
            for (int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if (dist[u] != INF && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if (dist[u] != INF && dist[u] + w < dist[v]) {
                throw new RuntimeException("Negative weight cycle detected");
            }
        }

        return dist;
    }

    public static int[] dijkstra(int src, int[] h) {
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{src, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0], d = curr[1];
            if (d > dist[u]) continue;

            for (int[] edge : adj[u]) {
                int v = edge[0], w = edge[1];
                int newDist = dist[u] + w + h[u] - h[v];
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pq.offer(new int[]{v, newDist});
                }
            }
        }

        for (int i = 0; i < V; i++) {
            if (dist[i] < INF)
                dist[i] = dist[i] - h[src] + h[i];
        }

        return dist;
    }

    public static int[][] johnson() {
        for (int i = 0; i < V; i++) {
            edges.add(new int[]{V, i, 0});
        }

        int[] h = bellmanFord(V);

        for (int u = 0; u < V; u++) {
            for (int[] edge : adj[u]) {
                int v = edge[0];
                edge[1] += h[u] - h[v];
            }
        }

        int[][] result = new int[V][V];
        for (int u = 0; u < V; u++) {
            result[u] = dijkstra(u, h);
        }

        return result;
    }

    public static void main(String[] args) {
        V = 5;
        adj = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        addEdge(0, 1, -1);
        addEdge(0, 2, 4);
        addEdge(1, 2, 3);
        addEdge(1, 3, 2);
        addEdge(1, 4, 2);
        addEdge(3, 2, 5);
        addEdge(3, 1, 1);
        addEdge(4, 3, -3);

        int[][] distances = johnson();

        System.out.println("Shortest distances between all pairs:");
        for (int i = 0; i < V; i++) {
            System.out.print("From node " + i + ": ");
            for (int j = 0; j < V; j++) {
                System.out.print((distances[i][j] == INF ? "INF" : distances[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
