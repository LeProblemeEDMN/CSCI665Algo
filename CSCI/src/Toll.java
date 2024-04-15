import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

public class Toll {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        //int origin=sc.nextInt();
        int origin=0;
        int dest=n-1;
       // int dest=sc.nextInt();
        int[][] edges=new int[3*n][3*n];
        Random RDM=new Random();
        for (int i = 0; i < m; i++) {
            /*int o=sc.nextInt();
            int d=sc.nextInt();
            int c=sc.nextInt();
            boolean toll=sc.nextInt()==1;*/
            int o= Math.abs(RDM.nextInt())%(n-3);
            int d=Math.abs(RDM.nextInt())%(n-o)+o;
            int c=Math.abs(RDM.nextInt())%15;
            boolean toll=Math.abs(RDM.nextInt())%15<2;
            if(toll){
                edges[o][d+n]=c;
                edges[d+n][o]=c;
                edges[o+n][d+2*n]=c;
                edges[d+2*n][o+n]=c;
            }else {
                for (int j = 0; j < 3; j++) {
                    edges[o + j * n][d + j * n] = c;
                    edges[d+j*n][o+j*n]=c;
                }
            }
        }

        long time=System.currentTimeMillis();
        int[] cost=new int[3*n];
        boolean[] included=new boolean[3*n];
        for (int i = 0; i < 3*n; i++)
                cost[i]=Integer.MAX_VALUE-100;

        cost[origin]=0;
        for (int i = 0; i < 3*n; i++) {
            int id_min=0;
            int minCost=Integer.MAX_VALUE-100;
            for (int j = 0; j < 3*n; j++)
                if(!included[j] && cost[j]<minCost){
                    id_min=j;
                    minCost=cost[j];
                }
            if(minCost>=Integer.MAX_VALUE-100)break;

            included[id_min]=true;
            for (int j = 0; j < 3*n; j++) {
                if(!included[j] && edges[id_min][j]>0 && cost[j]>cost[id_min]+edges[id_min][j]){
                    cost[j]=cost[id_min]+edges[id_min][j];
                }
            }
        }
        int min= Math.min(cost[dest],cost[dest+n]);
        min=Math.min(min,cost[2*n+dest]);

        System.out.println(System.currentTimeMillis()-time);

        if(min>=Integer.MAX_VALUE-100) System.out.println("-1");
        System.out.println(min);

    }
}
