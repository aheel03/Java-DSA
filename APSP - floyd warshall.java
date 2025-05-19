import java.util.*;
 
public class Main{
    static int n,m,q;
    static int[][] dis; 
 
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        q = sc.nextInt();
 
        dis = new int[n+1][n+1];
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==j) dis[i][j]=0;
                else dis[i][j]=1000000000;
            }
        }
 
        for(int i=0;i<m;i++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            int d=sc.nextInt();
            dis[a][b]=Math.min(d,dis[a][b]);
            dis[b][a]=Math.min(d,dis[b][a]);
        }
 
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    if(dis[i][k]+dis[k][j]<1000000000) dis[i][j]=Math.min(dis[i][j],dis[i][k]+dis[k][j]);
                }
            }
        }
 
        for(int qq=0;qq<q;qq++){
            int a=sc.nextInt();
            int b=sc.nextInt();
            if(dis[a][b]!=1000000000) System.out.println(dis[a][b]);
            else System.out.println(-1);
        }
    }
}
