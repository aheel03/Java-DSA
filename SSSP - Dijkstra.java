import java.util.*;

public class Main{
    static int n,m;
    static List<int[]>[] graph;
    static boolean[] vis;
    static int[] distance;

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n+1];
        distance = new int[n+1];

        for(int i=1;i<=n;i++){
            graph[i] = new ArrayList<>();
            distance[i] = 1000000000;
        }

        distance[1]=0;

        for(int i=0;i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int dis = sc.nextInt();
            graph[a].add(new int[]{b, dis});
        }

        vis = new boolean[n+1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
        pq.add(new int[]{0,1});
        while(!pq.isEmpty()){
            int[] edge=pq.poll();
            int dist=edge[0];
            int node=edge[1];
            if (dist>distance[node]) continue;
            for (int[] child : graph[node]) {
                int to = child[0];
                int weight = child[1];
                if (distance[node] + weight < distance[to]) {
                    distance[to] = distance[node] + weight;
                    pq.add(new int[]{distance[to], to});
                }
            }
        }
        
        for(int i=1;i<=n;i++){
            System.out.print(distance[i]+" ");
        }
    }
}
