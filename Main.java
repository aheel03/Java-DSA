import java.util.*;

public class Main{

    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] graph_transpose;
    static boolean[] vis;
    static int[] par;
    static Stack<Integer> st=new Stack<>();
    static int count;


    static void dfs(int node){
        if(vis[node]==true) return;
        vis[node]=true;
        for(int child:graph[node]){
            if(!vis[child]) dfs(child);
        }
        st.push(node);
    }

    static void dfs2(int node){
        if(vis[node]==true) return;
        vis[node]=true;
        par[node]=count;
        for(int child:graph_transpose[node]){
            if(!vis[child]) dfs2(child);
        }
    }


    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();

        graph = new ArrayList[n+1];
        graph_transpose = new ArrayList[n+1];
        vis=new boolean[n+1];
        par=new int[n+1];

        for(int i=0;i<=n;i++){
            graph[i]=new ArrayList<>();
            graph_transpose[i]=new ArrayList<>();
        }


        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();

            graph[a].add(b);
            graph_transpose[b].add(a);
        }

        for(int i=1;i<=n;i++){
            if(!vis[i]){
                dfs(i);
            }
        }

        count=1;

        Arrays.fill(vis, false);

        while(!st.empty()){
            int tt=st.pop();
            if(!vis[tt]){
                dfs2(tt);
                count++;
            }
        }

        System.out.println(count-1);
        for(int i=1;i<=n;i++){
            System.out.print(par[i]+" ");
        }


        //System.out.print(n);
        sc.close();
    }
}