import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Flood {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input reading
        int n = sc.nextInt();
        int treshold = sc.nextInt();
        int dry_amount = sc.nextInt();
        int[] times=new int[n];
        int[] size=new int[n];
        for (int i = 0; i < n; i++) {
            times[i]=sc.nextInt();
            size[i]=sc.nextInt();
        }

        long begin=System.currentTimeMillis();
        int water=0;//water at any time
        int pointer=0;//id of the next crack that will open
        int time=0;//current time
        int maxW=0;//max water seen
        while (pointer<n){
            time=times[pointer];
            int nbC=1;
            int[] cracks=new int[n-pointer];
            cracks[0]=size[pointer];
            pointer++;
            while (pointer<n && times[pointer]==time){//add all cracks that open on this time interval
                insert_value(cracks,nbC,size[pointer]);
                pointer++;
                nbC++;
            }

            //loop until the situation come back to normal
            while (nbC>0){
                //fix one crack
                nbC=Math.max(0,nbC-1);
                //add the water that goes through the dam
                for (int i = 0; i < nbC; i++) {
                    water+=cracks[i];
                    cracks[i]++;
                }
                water=Math.max(0,water-dry_amount);
                maxW= Math.max(maxW,water);

                //check if there is a flood
                if(water>=treshold){
                    System.out.println("FLOOD");
                    System.out.println(time);
                    System.out.println(water);
                    System.err.println(System.currentTimeMillis()-begin);
                    return;
                }

                time++;//next step
                while (pointer<n && times[pointer]==time){//add all cracks that open on this time interval
                    insert_value(cracks,nbC,size[pointer]);
                    pointer++;
                    nbC++;
                }
            }
            if(pointer<n){
                int nextTime=times[pointer];
                //remove the water that will be absorbed before a new cracks appear.
                water=Math.max(0,water-dry_amount*(nextTime-time));
            }
        }
        System.out.println("SAFE");
        System.out.println(maxW);
        //System.err.println(System.currentTimeMillis()-begin);
    }
    public static void insert_value(int[] values,int nbC,int v){
        //insert a integer in a sorted array
        int ceil = 0;
        int floor = nbC - 1;
        //dichotomy while
        while (ceil <= floor) {
            int mid = ceil + (floor - ceil) / 2;
            //modify the bounds
            if (values[mid] == v) {
                ceil=mid;
                break;
            } else if (values[mid] < v) {
                ceil = mid + 1;
            } else {
                floor = mid - 1;
            }
        }
        //shift the array
        for (int i = nbC; i >ceil ; i--) {
            values[i]=values[i-1];
        }
        values[ceil]=v;
    }
}
