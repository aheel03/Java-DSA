import java.util.*;

public class kosarajuSCC{

    static int n, m;
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] graph_Transpose;
    static boolean[] vis;
    static Stack<Integer> st = new Stack<>();

    static void dfs(int node) {
        if (vis[node]) {
            return;
        }
        vis[node] = true;
        for (int child : graph[node]) {
            if (!vis[child]) {
                dfs(child);
            }
        }
        st.push(node);
    }

    static void dfs_Transpose(int node) {
        if (vis[node]) {
            return;
        }
        vis[node] = true;
        for (int child : graph_Transpose[node]) {
            if (!vis[child]) {
                dfs_Transpose(child);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n+1];
        graph_Transpose = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
            graph_Transpose[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].add(y);   
            graph_Transpose[y].add(x);   
        }

        vis = new boolean[n+1];
        for (int i = 1; i <= n; i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        vis = new boolean[n + 1];
        int count = 0; 

        while (!st.empty()) {
            int nodeToDFS = st.pop();
            if (!vis[nodeToDFS]) {
                dfs_Transpose(nodeToDFS);
                count++;
            }
        }
        
        System.out.println("# of strongly Connected Components : " + count);
        sc.close();
    }
}