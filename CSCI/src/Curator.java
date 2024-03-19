import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Curator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int W = sc.nextInt();
        ArrayList<Integer>[] objectsW=new ArrayList[n];
        ArrayList<Integer>[] objectsV=new ArrayList[n];
        for (int i = 0; i < n; i++) {
            int w=sc.nextInt();
            int v=sc.nextInt();
            int t=sc.nextInt();
            if(objectsV[t]==null){
                objectsW[t]=new ArrayList<Integer>();
                objectsV[t]=new ArrayList<Integer>();
            }
            objectsW[t].add(w);
            objectsV[t].add(v);
        }
        //regroup
        int[] wt=new int[n];
        int[] val=new int[n];
        int[] indices=new int[n+1];
        int id=0;
        int idG=0;
        for (int i = 0; i < n; i++) {
            if(objectsV[i]!=null){
                indices[idG]=id;
                idG++;
                for (int j = 0; j < objectsV[i].size(); j++) {
                    wt[id]=objectsW[i].get(j);
                    val[id]=objectsV[i].get(j);
                    id++;
                }
            }
        }
        idG++;
        indices[idG]=id;

        //knaspack normal mais Knapsack(n,W) =max(cn + Knapsack(n-1, W-w_n), Knapsack(n-1, W)) devient
        //Knapsack(n,W) =max(cn + Knapsack(indice last type, W-w n), Knapsack(n-1, W))

        int[][] dp = new int[n+1][W+1];
        int group=1;
        // Build dp[][] in bottom-up manner
        for (int i = 1; i <= n; i++) {

            if(i-1>=indices[group]){
                group=Math.min(group+1,idG-1);
            }

            for (int w = 1; w <= W; w++) {
                if (wt[i-1] <= w) {
                    dp[i][w] = Math.max(val[i - 1] + dp[indices[group - 1]][w - wt[i - 1]], dp[i - 1][w]);
                }else
                    dp[i][w] = dp[i-1][w];
            }
        }

        System.out.println(dp[n][W]);
    }
}
