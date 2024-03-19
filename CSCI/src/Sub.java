import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Sub {
   public static void main(String[]args){
       Scanner sc = new Scanner(System.in);
       int n = sc.nextInt();

       List<int[]>jobsA=new ArrayList<>();

       for (int i = 0; i < n; i++) {
           int b=sc.nextInt();
           int e=sc.nextInt();
           int c=sc.nextInt();
           jobsA.add(new int[]{b,e,c});

       }
       //sort the job by increasing finishing time
       Comparator<int[]>comp=new Comparator<int[]>() {
           @Override
           public int compare(int[] o1, int[] o2) {
               return Integer.compare(o1[1],o2[1]);
           }
       };
       jobsA.sort(comp);

        //array
       int[][][] array=new int[n+1][4][2];//A B 2A 2B

       //fill the array
       for (int i = 1; i <= n; i++) {
           int begin=jobsA.get(i-1)[0];
           int end=jobsA.get(i-1)[1];
           int contractor=jobsA.get(i-1)[2];
           //copy the previous values
           for (int j = 0; j < 4; j++) {
               array[i][j][0]=array[i-1][j][0];
               array[i][j][1]=array[i-1][j][1];
           }
           //if we switch from contractor
           //check if the the new contract begin after the end of the previous and if the new length is superior to the previous
            if(array[i-1][1-contractor][1]<=begin && array[i-1][contractor][0]<array[i-1][1-contractor][0]+1){
               array[i][contractor][0]=array[i-1][1-contractor][0]+1;
               array[i][contractor][1]=end;
           }
           if(array[i-1][3-contractor][1]<=begin && array[i][contractor][0]<array[i-1][3-contractor][0]+1){
               array[i][contractor][0]=array[i-1][3-contractor][0]+1;
               array[i][contractor][1]=end;
           }

           //if we stay with the same contractor for two consecutive contracts.
           if(array[i-1][contractor][1]<=begin && array[i-1][2+contractor][0]<array[i-1][contractor][0]+1){
               array[i][2+contractor][0]=array[i-1][contractor][0]+1;
               array[i][2+contractor][1]=end;
           }

       }
        //find the number of contract
       int m=array[n][0][0];
       for (int i = 1; i < 4; i++) {
          m= Math.max(m,array[n][i][0]);
       }
       System.out.println(m);
   }
}


