// https://cses.fi/problemset/task/1675/
import java.util.*;

public class Main{

    static int n,m;
    static int[] parent;
    static int[] setSize;
    static int[][] edges;

    static void makeSet(int node){
        parent[node]=node;
        setSize[node]=1;
    }

    static int findSet(int node){
        if(node==parent[node]) return node;
        return parent[node]=findSet(parent[node]);
    }

    static void unionSets(int a, int b){
        a=findSet(a);
        b=findSet(b);
        if(a!=b){
            if(setSize[a]>setSize[b]){
                parent[b]=a;
                setSize[a]+=setSize[b];
            }else{
                parent[a]=b;
                setSize[b]+=setSize[a];
            }
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        parent = new int[n+1];
        edges = new int[m][3];
        setSize = new int[n+1];
        for(int i=0;i<m;i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int wt = sc.nextInt();
            edges[i][0]=wt;
            edges[i][1]=a;
            edges[i][2]=b;
        }

        Arrays.sort(edges, Comparator.comparingInt(a -> a[0]));
        for(int i=1;i<=n;i++) makeSet(i);

        long totalCost=0;
        int edgecount=0;

        for(int[] edge:edges){
            int cost=edge[0];
            int u=edge[1];
            int v=edge[2];
            if(findSet(u)!=findSet(v)){
                unionSets(u,v);
                totalCost+=cost;
                edgecount++;
                if(edgecount==n-1) break;
            }
        }

        if(edgecount==n-1){
            System.out.println(totalCost);
        }else{
            System.out.println("IMPOSSIBLE");
        }
    }
}
