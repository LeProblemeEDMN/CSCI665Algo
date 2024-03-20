import java.util.Arrays;
import java.util.Scanner;

public class LAIS2 {

    public static void computeA(int i,int j,int[][][] array,int current_value){
        int best=array[i][j-1][0];
        int v_best=array[i][j-1][2];
        for (int k = 0; k <= j; k++) {
            int nl=1+array[i-1][k][1];
            if(array[i-1][k][3]<current_value && (nl>best || (nl==best && current_value<v_best))){
                best=nl;
                v_best=current_value;
            }
        }
        array[i][j][0]=best;
        array[i][j][2]=v_best;
    }

    public static void computeB(int i,int j,int[][][] array,int current_value){
        int best=array[i-1][j][1];
        int v_best=array[i-1][j][3];

        for (int k = 0; k <= i; k++) {
            int nl=1+array[k][j-1][0];
            if(array[k][j-1][2]<current_value && (nl>best || (nl==best && current_value<v_best))){
                best=nl;
                v_best=current_value;
            }
        }
        array[i][j][1]=best;
        array[i][j][3]=v_best;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nB = sc.nextInt();
        int nA = sc.nextInt();
        int[] A=new int[nA];
        int[] B=new int[nB];
        for (int i = 0; i < nB; i++) {
            B[i]=sc.nextInt();
        }
        for (int i = 0; i < nA; i++) {
            A[i]=sc.nextInt();
        }

        int[][][] array=new int[1+nA][1+nB][4];
        for (int i = 1; i <= nA; i++) {
            array[i][0][0]=1;
            array[i][0][2]=A[i-1];
        }
        for (int i = 1; i <= Math.min(nA,nB); i++) {
            for (int j = i; j <= nB; j++) {
                computeB(i,j,array,B[j-1]);
                computeA(i,j,array,A[i-1]);
            }
            for (int j = i+1; j <= nA; j++) {
                computeA(j,i,array,A[j-1]);
                computeB(j,i,array,B[i-1]);
            }
        }

        int max=0;
        for (int i = 0; i <= nA; i++) {
            for (int j = 0; j <= nB; j++) {
                max=Math.max(max,array[i][j][0]);
                max=Math.max(max,array[i][j][1]);
            }
        }
        System.out.println(max);
    }
}
