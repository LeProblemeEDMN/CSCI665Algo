import java.util.Scanner;
/*
Baptiste Guilbery
 */
public class Snowfall {
        public static void main(String[] args) {
            Scanner sc=new Scanner(System.in);
            int n=sc.nextInt();
            int[] snow=new int[n+1];
            /*for (int i = 0; i < n; i++) {
                snow[i+1]=sc.nextInt();
            }*/
            long t=System.currentTimeMillis();
            //begin algorithm
            double seuil=snow[n]/2.0;

            for (int i = 3; i <= n; i++) {

                if(snow[i]-snow[i-3]>seuil){//check if during the 3 day period the snowfall is superior to half of the total snowfall
                    System.out.println("YES");
                    System.out.println(System.currentTimeMillis()-t);
                    return;
                }
            }
            System.out.println("NO");
            System.out.println(System.currentTimeMillis()-t);
        }

}
