import java.util.Arrays;
import java.util.Scanner;

public class Fewest {
    public static int[] radixSort(int[] array,int maxExp){
        //radix sort implementation
        int power=1;
        //loop over each digit
        for (int p = 1; p <= maxExp; p++) {
            int oldpower=power;
            power=10*power;
            int[] count=new int[10];
            //count the number of digit in the array
            for (int i = 0; i < array.length; i++) {
                int digit=array[i]%power/oldpower;
                count[digit]++;
            }

            int[] placeIn=new int[10];
            for (int i = 1; i < 10; i++) {
                placeIn[i]=placeIn[i-1]+count[i-1];
            }
            //create a new array
            int[] newArray=new int[array.length];
            //insert the number in the position depending of the digit
            for (int i = 0; i < array.length; i++) {
                int digit=array[i]%power/oldpower;
                newArray[placeIn[digit]]=array[i];

                placeIn[digit]++;
            }
            array=newArray;
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int obj=sc.nextInt();
        int[] count=new int[n];
        for (int i = 0; i < n; i++) {
            count[i]=sc.nextInt();
        }
        int max=0;
        int sum=0;
        for (int i = 0; i < n; i++) {
            sum+=count[i];
            max=Math.max(max,count[i]);
        }
        max=(int)Math.ceil(Math.log10(max));
        count=radixSort(count,max);
        int nb=0;
        int partialSum=0;

        while (partialSum<=obj){
            partialSum+=count[n-1-nb];
            nb++;
        }
        System.out.println(nb);
    }
}
