import java.util.*;

public class Main{
    static int n,m,q;
    static int[] dis;
    static List<int[]>[] graph;
    static boolean updated;

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n+1];
        dis = new int[n+1];
        
        for(int i=1;i<=n;i++){
            graph[i]=new ArrayList<>();
            dis[i]=1000000000;
        }
        dis[1]=0;

        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int wt=sc.nextInt();
            graph[a].add(new int[]{b,wt});
        }

        for(int c=0;c<n-1;c++){
            updated = false;
            for(int node=1;node<=n;node++){
                if(dis[node]==1000000000) continue;
                for(int[] edge:graph[node]){
                    int wt=edge[1];
                    int child=edge[0];
                    if(dis[child]>dis[node]+wt){
                        dis[child]=dis[node]+wt;
                        updated=true;
                    }
                }
            }
            if(!updated)break;
        }

        System.out.println(dis[n]);

    }
}
