import java.util.*;

public class Fewest {
    public static int k_select(int[] array,int begin,int end,int k){
        //split the array in two parts
        int pivotIndex = split(array, begin, end);

        //if pivot is at inex k it is the wanted value
        if (pivotIndex == k) {
            return array[pivotIndex];

        //otcherwise we need to redo this on the other half of the array.
        } else if (pivotIndex < k) {
            return k_select(array, pivotIndex + 1, end, k);
        } else {
            return k_select(array, begin, pivotIndex - 1, k);
        }
    }

    private static int split(int[] array, int begin, int end) {
        //split the array into two half
        int pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j] < pivot) {
                i++;
                int c=array[i];
                array[i]=array[j];
                array[j]=c;
            }
        }
        int c=array[i + 1];
        array[i + 1]=array[end];
        array[end]=c;
        return i + 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int obj=sc.nextInt();


        int[] count=new int[n];
        for (int i = 0; i < n; i++) {
            //count[i]=sc.nextInt();

        }


        int sum=0;
        int min=9999999,max=-9999999;
        for (int i = 0; i < n; i++) {
            sum+=count[i];
            min=Math.min(min,count[i]);
            max=Math.max(max,count[i]);
        }

        int begin=0;int end=n-1;
        int number=0;
        //we end teh algorithm if all the number in the array are the same or if there is only one number
        while (min!=max && begin<end){

            int k=(end-begin)/2+begin;
            //k select
            k_select(count,begin,end,k);
            int partial_sum=0;
            for (int i = begin; i < k; i++) {
                partial_sum+=count[i];
            }
            //we will now work on only one half of the array
            if(sum-partial_sum>obj){
                begin=k;
            }else{
                number+=end-k+1;
                end=k-1;
                obj-=sum-partial_sum;
            }

            sum=0;
            min=9999999;max=-9999999;
            for (int i = begin; i <= end; i++) {
                sum+=count[i];
                min=Math.min(min,count[i]);
                max=Math.max(max,count[i]);
            }
        }
        //add the final number needed.
        if(begin+1>=end) {
            number++;
        }else{
            int nb=count[begin];
            number+=obj/nb+1;
        }
        System.out.println(number);
    }
}
