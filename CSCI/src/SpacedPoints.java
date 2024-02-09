import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SpacedPoints {
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
                newArray[placeIn[digit]][0]=array[i][0];
                newArray[placeIn[digit]][1]=array[i][1];
                placeIn[digit]++;
            }
            array=newArray;
        }
        return array;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input reading
        int n = sc.nextInt();
        int[][] points=new int[n][2];
        for (int i = 0; i < n; i++) {
            points[i][0]=sc.nextInt();
            points[i][1]=sc.nextInt();
        }

        long begin=System.currentTimeMillis();
        int maxExp=0;//maximum number of digit of a number
        for (int i = 0; i < points.length; i++) {
            maxExp=Math.max(maxExp,Math.max(points[i][0],points[i][1]));
        }
        maxExp=(int)Math.ceil(Math.log10(maxExp));

        //look for the points on same X-axis
        points=radixSort(points,1,maxExp);
        points=radixSort(points,0,maxExp);
        int max=0;
        for (int i = 0; i < n; i++) {
            int x=points[i][0];
            int l=1;
            //count the number of points on the same X line
            while (i+l<n && points[i+l][0]==x)l++;
            if(l>=2){

                int step=points[i+1][1]-points[i][1];
                //check if the increments are the same.
                for (int j = 2; j < l; j++) {
                    if(step!=points[i+j][1]-points[i+j-1][1]){
                        i+=l;
                        l=0;
                        break;
                    }
                }
            }
            i+=l-1;

            max=Math.max(max,l);
        }

        //look for the points on same Y-axis
        points=radixSort(points,0,maxExp);
        points=radixSort(points,1,maxExp);

        for (int i = 0; i < n; i++) {
            int x=points[i][1];
            int l=1;
            //count the number of points on the same X line
            while (i+l<n && points[i+l][1]==x)l++;
            if(l>=2){
                int step=points[i+1][0]-points[i][0];
                //check if the increments are the same.
                for (int j = 2; j < l; j++) {
                    if(step!=points[i+j][0]-points[i+j-1][0]){
                        i+=l;
                        l=0;
                        break;
                    }
                }
            }
            i+=l-1;

            max=Math.max(max,l);
        }
        System.out.println(max);
        //System.err.println(System.currentTimeMillis()-begin);
    }
}

/*int[] ax=new int[n];
        int[] bx=new int[n];
        int[] ay=new int[n];
        int[] by=new int[n];
        for (int i = 0; i < n; i++) {
            ax[i]=-1;
            ay[i]=-1;
        }
        //count the number per line and columns
        int[] cx=new int[n];
        int[] cy=new int[n];

        for (int i = 0; i < n; i++) {
            int x=X[i];
            int y=Y[i];
            if(cx[x]<0) continue;//can be only evenly spaced points on this line
            if(ax[x]==-1){//first point to go on this line
                ax[x]=y;
                cx[x]=1;
            }else if(bx[x]==0){
                bx[x]=Math.abs(y-ax[x]);
                cx[x]=2;
            }else{
                int dy=Math.abs(y-ax[x]);
                if(dy<bx[x]){//if dy<step we exchange them because maybe step=M*dy
                    int p=dy;
                    dy=bx[x];
                    bx[x]=p;
                }
                if(dy%bx[x]!=0){
                    cx[x]=-1;
                }else cx[x]++;
            }
        }

        for (int i = 0; i < n; i++) {
            int x=X[i];
            int y=Y[i];
            if(cy[y]<0) continue;//can be only evenly spaced points on this line
            if(ay[y]==-1){//first point to go on this line
                ay[y]=x;
                cy[y]=1;
            }else if(by[y]==0){
                by[y]=Math.abs(x-ay[y]);
                cy[y]=2;
            }else{
                int dx=Math.abs(x-ay[y]);
                if(dx<by[y]){//if dy<step we exchange them because maybe step=M*dy
                    int p=dx;
                    dx=by[y];
                    by[y]=p;
                }
                if(dx%by[y]!=0){
                    cy[y]=-1;
                }else cy[y]++;
            }
        }

        int max=0;
        for (int i = 0; i < n; i++) {
            max=Math.max(max,Math.max(cx[i],cy[i]));
        }*/
