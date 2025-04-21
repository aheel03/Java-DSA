import java.util.*;

public class SubmergingIslands{

    static final int MAX_N = 10005;
    static List<Integer>[] adj = new ArrayList[MAX_N];
    static int[] disc = new int[MAX_N];
    static int[] low = new int[MAX_N];
    static int[] parent = new int[MAX_N];
    static boolean[] isArticulationPoint = new boolean[MAX_N];
    static int timer;

    static void dfsAP(int u) {
        disc[u] = low[u] = ++timer;
        int children = 0;

        for (int v : adj[u]) {
            if (disc[v] == -1) {
                children++;
                parent[v] = u;
                dfsAP(v);
                low[u] = Math.min(low[u], low[v]);

                if (parent[u] != -1 && low[v] >= disc[u]) {
                    isArticulationPoint[u] = true;
                }
            } else if (v != parent[u]) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }

        if (parent[u] == -1 && children > 1) {
            isArticulationPoint[u] = true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            if (n == 0 && m == 0) break;

            for (int i = 1; i <= n; i++) {
                adj[i] = new ArrayList<>();
                disc[i] = -1;
                low[i] = 0;
                parent[i] = -1;
                isArticulationPoint[i] = false;
            }
            timer = 0;

            for (int i = 0; i < m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                adj[u].add(v);
                adj[v].add(u);
            }

            for (int i = 1; i <= n; i++) {
                if (disc[i] == -1) {
                    dfsAP(i);
                }
            }

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (isArticulationPoint[i]) count++;
            }

            System.out.println(count);
        }

        sc.close();
    }
}
