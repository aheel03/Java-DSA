import java.util.*;

public class Main{
    static int n,m;
    static List<int[]>[] graph;
    static boolean[] vis;

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int wt = sc.nextInt();
            graph[a].add(new int[]{wt,b});
            graph[b].add(new int[]{wt,a});
        }

        vis = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        pq.add(new int[]{0,1});
        long totalCost=0;
        int count=0;
        while(!pq.isEmpty()){
            int[] edge=pq.poll();
            int cost=edge[0];
            int node=edge[1];

            if(vis[node]){
                continue;
            }
            vis[node]=true;
            totalCost+=cost;
            count++;
            for(int[] child:graph[node]){
                if(!vis[child[1]]){
                    pq.add(new int[]{child[0],child[1]});
                }
            }
        }

        if(count==n){
            System.out.println(totalCost);
        }else{
            System.out.println("IMPOSSIBLE");
        }    
    }
}
