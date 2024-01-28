import java.util.*;

public class SortingTest {
    /*
    5
    5
    4
    3
    2
    1

     */
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        //int n=sc.nextInt();
        int n=5000000;
        long dt=0;
        for (int j = 0; j < 30; j++) {

            Random RDM=new Random();
            int[]l=new int[n];
            for (int i = 0; i < n; i++) {
                ///l[i]=sc.nextInt();
                l[i]=RDM.nextInt();
            }
            long time=System.currentTimeMillis();
            l=sort(l);
            dt+=System.currentTimeMillis()-time;
        }
        System.out.println(dt/30);
    }

    public static int[] sort(int[] list){
        if(list.length==1)return list;
        if(list.length==2) {
            if(list[1]>list[0])return list;
            return new int[]{list[1],list[0]};
        }
        int halfSize=list.length/2;

        int[]l1=new int[halfSize];
        for (int i = 0; i < halfSize; i++) {
            l1[i]=list[i];
        }
        int[]l2=new int[list.length-halfSize];
        for (int i = 0; i < l2.length; i++) {
            l2[i]=list[i+halfSize];
        }

        l1=sort(l1);
        l2=sort(l2);
        int[]finalList=new int[list.length];
        int c1=0;
        int c2=0;

        for (int c3 = 0; c3 < finalList.length; c3++) {
            if(c2<l2.length && (c1>=l1.length || l1[c1]>l2[c2])){
                finalList[c3]=l2[c2];
                c2++;
            }else{
                finalList[c3]=l1[c1];
                c1++;
            }
        }

        return finalList;
    }
}
