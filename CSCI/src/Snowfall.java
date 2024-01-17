import java.util.Scanner;

public class Snowfall {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int[] snow=new int[n+1];
            for (int i = 0; i < n; i++) {
                snow[i+1]=sc.nextInt();
            }

            //begin algorithm
            double seuil=snow[n]/2.0;
            for (int i = 3; i < n; i++) {
                if(snow[i]-snow[i-3]>=seuil){
                    System.out.println("YES");
                    return;
                }
            }
            System.out.println("NO");
        }

}
