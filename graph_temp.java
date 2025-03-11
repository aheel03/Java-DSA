import java.util.*;

class Graph{
    int nodeCount;
    LinkedList<Integer>[] g;
    boolean[] vis;

    public Graph(int nodes){
        this.nodeCount=nodes;
        g=new LinkedList[nodeCount];
        for(int i=0;i<nodeCount;i++){
            g[i]=new LinkedList<>();
        }
        vis=new boolean[nodeCount];
    }

    public void addEdge(int a, int b){
        g[a].add(b);
        g[b].add(a);
    }

    public void DFS(int node){
        if(vis[node]) return;
        vis[node]=true;
        System.out.println("visiting "+node);
        for(int child:g[node]){
            DFS(child);
        }
    }

    public void BFS(int root){
        Queue<Integer> q=new LinkedList<>();
        boolean visited[]=new boolean[nodeCount];
        q.add(root);
        visited[root]=true;

        while(!q.isEmpty()){
            int node=q.poll();
            System.out.println("visiting "+node);
            for(int child:g[node]){
                if(visited[child]) continue;
                q.add(child);
                visited[child]=true;
            }
        }
    }
}


public class graph_temp{
    public static void main(String[] args) {
        Graph graph = new Graph(7);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);

        System.out.println("DFS starting from node 0:");
        graph.DFS(0);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 6);

        System.out.println("BFS starting from node 0:");
        graph.BFS(0);
    }
}