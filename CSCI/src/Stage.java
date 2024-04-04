import java.util.Arrays;
import java.util.Scanner;

public class Stage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l1=sc.nextInt();
        int l2=sc.nextInt();
        int[] a1=new int[l1];
        int[] a2=new int[l2];
        for (int i = 0; i < l1; i++) {
            a1[l1-1-i]=sc.nextInt();
        }
        for (int i = 0; i < l2; i++) {
            a2[l2-1-i]=sc.nextInt();
        }
        int[][]dynA=new int[l1+1][l2+1];
        int[][]lastHeightA=new int[l1+1][l2+1];
        int[][]dynB=new int[l1+1][l2+1];
        int[][]lastHeightB=new int[l1+1][l2+1];
        ///init
        lastHeightA[0][0]=a1[0];
        for (int i = 1; i < l1+1; i++) {
            lastHeightA[i][0]=a1[i-1];
            dynA[i][0]=dynA[i-1][0]+Math.abs(a1[i-1]-lastHeightA[i-1][0]);
        }
        lastHeightB[0][0]=a2[0];
        for (int i = 1; i < l2+1; i++) {
            lastHeightB[0][i]=a2[i-1];
            dynB[0][i]=dynB[0][i-1]+Math.abs(a2[i-1]-lastHeightB[0][i-1]);
        }
        //i pr les i prmeir de A1 j pr les j premier de A2
        for (int i = 1; i < l1+1; i++) {
            for (int j = 1; j < l2+1; j++) {
                int c1=dynA[i-1][j]+Math.abs(a1[i-1]-lastHeightA[i-1][j]);
                int c2=dynB[i-1][j]+Math.abs(a1[i-1]-lastHeightB[i-1][j]);
                if(c1>c2){
                    c1=c2;
                }
                dynA[i][j]=c1;
                lastHeightA[i][j]=a1[i-1];

                c1=dynA[i][j-1]+Math.abs(a2[j-1]-lastHeightA[i][j-1]);
                c2=dynB[i][j-1]+Math.abs(a2[j-1]-lastHeightB[i][j-1]);
                if(c1>c2){
                    c1=c2;
                }
                dynB[i][j]=c1;
                lastHeightB[i][j]=a2[j-1];
            }
        }

        System.out.println(Math.min(dynA[l1][l2],dynB[l1][l2]));
    }
}
