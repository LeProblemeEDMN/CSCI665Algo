import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Ski {
    public static int[][] radixSort(int[][] array,int idDim,int maxExp){
        //radix sort implementation
        int power=1;
        //loop over each digit
        for (int p = 1; p <= maxExp; p++) {
            int oldpower=power;
            power=10*power;
            int[] count=new int[10];
            //count the number of digit in the array
            for (int i = 0; i < array.length; i++) {
                int digit=array[i][idDim]%power/oldpower;
                count[digit]++;
            }

            int[] placeIn=new int[10];
            for (int i = 1; i < 10; i++) {
                placeIn[i]=placeIn[i-1]+count[i-1];
            }
            //create a new array
            int[][] newArray=new int[array.length][2];
            //insert the number in the position depending of the digit
            for (int i = 0; i < array.length; i++) {
                int digit=array[i][idDim]%power/oldpower;
                newArray[placeIn[digit]]=array[i];
                newArray[placeIn[digit]]=array[i];
                placeIn[digit]++;
            }
            array=newArray;
        }
        return array;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int[][][] array=new int[m][n][4];
        int[][] sort_array=new int[m*n][4];
        int max_h=0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                array[i][j][2]=sc.nextInt();
                array[i][j][0]=i;
                array[i][j][1]=j;
                max_h= Math.max(max_h,array[i][j][2]);
                sort_array[i+j*m]=array[i][j];
            }
        }
        sort_array=radixSort(sort_array,2,(int)Math.ceil(Math.log10(max_h)));

        int maxLength=0;
        for (int i = 0; i < m*n; i++) {
            int x=sort_array[m*n-1-i][0];
            int y=sort_array[m*n-1-i][1];
            int z=sort_array[m*n-1-i][2];
            int h=sort_array[m*n-1-i][3];
            for (int j = -1; j < 2; j++) {
                for (int k = -1; k < 2; k++) {
                    if((j==0 && k==0) || x+j<0 || x+j>=m || y+k<0 ||y+k>=n)continue;
                    if(array[x+j][y+k][2]<z && array[x+j][y+k][3]<=h){
                        array[x+j][y+k][3]=h+1;
                        maxLength= Math.max(maxLength,h+1);
                    }
                }
            }
        }
        System.out.println(maxLength);
    }
}
