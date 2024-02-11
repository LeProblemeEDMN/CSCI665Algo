import java.util.Arrays;
import java.util.Scanner;

public class Gymnast {

    public static int mergeSort(double[] array){
        //end condition
        if(array.length<=1){
            return 0;
        }
        //mid array
        int mid=array.length/2;
        //divide
        double[] a1=new double[mid];
        for (int i = 0; i < mid; i++) {
            a1[i]=array[i];
        }
        int inv1=mergeSort(a1);

        double[] a2=new double[array.length-mid];
        for (int i = mid; i < array.length; i++) {
            a2[i-mid]=array[i];
        }
        int inv2=mergeSort(a2);
        int i1=0,i2=0;
        int nbInv=0;

        //merge
        while (i1<a1.length && i2<a2.length){
            if(a1[i1]<a2[i2]){
                array[i1+i2]=a1[i1];
                i1++;
            }else{

                nbInv+=mid-i1;
                array[i1+i2]=a2[i2];
                i2++;
            }
        }

        while (i1+i2<array.length){
            if(i1<a1.length){
                array[i1+i2]=a1[i1];
                i1++;
            }else{
                array[i1+i2]=a2[i2];
                i2++;
            }
        }
        //Return the number of somersault
        return nbInv+inv2+inv1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input reading
        int n = sc.nextInt()/2;
        int[] order= new int[2*n];
        for (int i = 0; i < order.length; i++) {
            order[i]=sc.nextInt();
        }
        //long time=System.currentTimeMillis();
        //compute the orde rin which the gymnast need to be to make the minimum of somersault.
        double[] values=new double[2*n];
        {
            //order of each country
            int[] value = new int[n];
            boolean[] alreadySeen = new boolean[n];//true if during the above loop we have already seen one gymnast of this country
            int nbSeen=0;
            for (int i = 0; i < 2*n; i++) {
                double v=0;//value of the gymnast=order of the country +0.5 if he is the second one
                if(!alreadySeen[order[i]]){
                    value[order[i]]=nbSeen;
                    nbSeen++;
                    alreadySeen[order[i]]=true;
                }else v=0.5;
                v+=value[order[i]];
                values[i]=v;
            }
        }
        //now that we know the order we use emrge sort to compute hte number of somersault.
        System.out.println(mergeSort(values));


    }
}
