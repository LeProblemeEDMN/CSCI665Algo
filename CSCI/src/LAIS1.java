import java.util.Arrays;
import java.util.Scanner;

public class LAIS1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int nA = sc.nextInt();
        int nB = sc.nextInt();
        int[] A=new int[nA];
        for (int i = 0; i < nA; i++) {
           A[i]=sc.nextInt();
        }
        int[] B=new int[nB];
        for (int i = 0; i < nB; i++) {
            B[i]=sc.nextInt();
        }

        int[] seqA;
        int[] seqB;
        if(nA!=nB){
        if(Math.min(nA,nB)==nA){
            seqA=new int[Math.min(nA,nB)+1];
            seqB=new int[Math.min(nA,nB)+2];
            int max=B[nA];
            for (int i = nA+1; i < nB; i++)
                max= Math.max(B[i],max);
            B[nA]=max;
        }else{
            seqA=new int[Math.min(nA,nB)+2];
            seqB=new int[Math.min(nA,nB)+1];
            int max=A[nB];
            for (int i = nB+1; i < nA; i++)
                max= Math.max(A[i],max);
            A[nB]=max;
        }}else{
            seqA=new int[Math.min(nA,nB)+1];
            seqB=new int[Math.min(nA,nB)+1];
        }


        for (int i = 1; i < Math.min(nA,nB)+2; i++) {
            //System.out.println(i+" "+lastA+" "+lastB);
            //int newA=lastA;
            //int newB=lastB;
            if(i>nA){

            }else{
                int max=1;
                for (int j = 1; j < Math.min(nB+1,i); j++) {
                    int v=seqB[j]+1;

                    if(A[i-1]>B[j-1])max=Math.max(v,max);

                }

                seqA[i]=max;
            }

            if(i>nB){

            }else{
                int max=1;
                for (int j = 1; j < Math.min(nA+1,i); j++) {
                    int v=seqA[j]+1;
                    //System.out.print("("+v+","+A[j-1]+","+B[i-1]+") ");
                    if(B[i-1]>A[j-1])max=Math.max(v,max);

                }
                seqB[i]=max;
            }

        }
        int max=1;
        for (int i = 0; i < seqA.length; i++)
            max= Math.max(seqA[i],max);
        for (int i = 0; i < seqB.length; i++)
            max= Math.max(seqB[i],max);

        System.out.println(max);
    }
}
