import java.util.*;

public class Clearance {
    public static void main(String[] args){
        Scanner sc =new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[] clearances=new int[n];
        int[][] edges=new int[n][n];
        for (int i = 0; i <n ; i++) {
            clearances[i]=sc.nextInt();
        }
        for (int i = 0; i < m; i++) {
            int o=sc.nextInt();
            int d=sc.nextInt();
            int c=sc.nextInt();

            edges[o][d]=c;
            edges[d][o]=c;
        }
        int totalCost=0;
        int[] parent=new int[n];
        int[] key=new int[n];
        boolean[] included=new boolean[n];
        for (int j = 0; j < n; j++)
            key[j] = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if(clearances[i]==1){
                key[i] = 0;
                parent[i] = -1;
                break;
            }
        }
        for (int i = 1; i <= 3; i++) {

            for (int j = 0; j < n-1; j++) {
                int min = Integer.MAX_VALUE;
                int min_index = -1;

                for (int k = 0; k < n; k++)
                    if (!included[k] && key[k] < min && clearances[k]<=i) {
                        min = key[k];
                        min_index = k;
                    }
                if(min_index<0)break;
                if(parent[min_index]>=0){
                    totalCost+=edges[parent[min_index]][min_index];
                }

                included[min_index]=true;
                for (int k = 0; k < n; k++) {
                    if(!included[k] && edges[min_index][k]!=0 && edges[min_index][k]<key[k]){
                        key[k]=edges[min_index][k];
                        parent[k]=min_index;
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                if(clearances[j]==i && !included[j]){
                    System.out.println("-1");
                   // return;
                }
            }

        }

        System.out.println(totalCost);
    }
}
class Edge{
    int origin;
    int destination;
    int cost;
    int clearance;

    public Edge(int origin, int destination, int cost) {
        this.origin = Math.min(origin,destination);
        this.destination = Math.max(destination,origin);
        this.cost = cost;
    }

}
