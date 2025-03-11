import java.util.*;

class Graph{
    int nodeCount;
    ArrayList<Integer>[] g;
    boolean[] vis;
    int[] par;

    public Graph(int nodes){
        this.nodeCount=nodes+1;
        g=new ArrayList[nodeCount];
        vis=new boolean[nodeCount];
        par=new int[nodeCount];
        for(int i=0;i<nodeCount;i++){
            g[i]=new ArrayList<>();
            par[i]=-1;
        }
        
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
        Queue<Integer> q=new ArrayDeque<>();
        q.add(root);
        vis[root]=true;

        while(!q.isEmpty()){
            int node=q.poll();
            for(int child:g[node]){
                if(vis[child]) continue;
                par[child]=node;
                q.add(child);
                vis[child]=true;
            }
        }
    }

    boolean isVisited(int node){
        return vis[node];
    }

    public void PrintPath(int dest){
        ArrayList<Integer> path=new ArrayList<>();
        int curr=dest;
        while(par[curr]!=-1){
            path.add(curr);
            curr=par[curr];
        }
        path.add(1);
        int n=path.size();
        System.out.println(n);
        for(int i=n-1;i>=0;i--){
            System.out.print(path.get(i)+" ");
        }
    }
}

public class Main{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        Graph graph=new Graph(n);
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            graph.addEdge(a, b);
        }

        graph.BFS(1);

        if(graph.isVisited(n)){
            graph.PrintPath(n);
        }else{
            System.out.println("IMPOSSIBLE");
        }

        sc.close();
        
    }
}
