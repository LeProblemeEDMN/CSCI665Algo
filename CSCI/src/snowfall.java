import java.util.Scanner;


public class snowfall {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] snow=new int[n];
        for (int i = 0; i < n; i++) {
            snow[i]=sc.nextInt();
        }

        //begin algorithm
        int sum=snow[0]+snow[1]+snow[2];
        int bigger_3d_interval=sum;
        int current_3d_interval=sum;
        for (int i = 3; i < n; i++) {
            sum+=snow[i];
            current_3d_interval+=snow[i]-snow[i-3];
            if(current_3d_interval>bigger_3d_interval)bigger_3d_interval=current_3d_interval;
        }
        if(2*bigger_3d_interval>sum){
            System.out.println("YES");
        }else System.out.println("NO");
    }
}