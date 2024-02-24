import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;


public class Angles {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        //input
        int n=sc.nextInt();
        Vector[] points=new Vector[n];
        for (int i = 0; i < n; i++) {
            points[i]=new Vector(sc.nextInt(),sc.nextInt());
        }

        int count=0;//numebr of right triangle
        for (int i = 0; i < n; i++) {

            double[] dxCoordsp=new double[n-1];//oversized array of the ratios x/y
            double[] dxCoordsn=new double[n-1];//oversized array of the ratios -y/x
            int p_c=0;//real size of the array
            int n_c=0;//real size of the array
            int nbZx=0;//number of vector where x=0
            int nbZy=0;//number of vector where y=0
            for (int j = 0; j < n; j++) {
                if(j==i)continue;
                Vector dC=points[i].get_substract(points[j]);

                //cont vector where x or y equals 0
                if(dC.getX()==0){
                    nbZx++;
                    continue;
                }
                if(dC.getY()==0){
                    nbZy++;
                    continue;
                }

                //flip vector such that X>0.
                if(dC.getX()<0){
                    dC.mul(-1);
                }
                //split in the two arrays
                if(dC.getY()>0){
                    dxCoordsp[p_c]= dC.x/dC.y;
                    p_c++;
                }else {
                    dxCoordsn[n_c]= -dC.y/dC.x;
                    n_c++;
                }
            }
            count+=nbZx*nbZy;//the vectors where x=0 and y=0 form right triangles

            if(p_c==0 || n_c==0) continue;
            dxCoordsp=Arrays.copyOf(dxCoordsp,p_c);//reduce the arrays
            dxCoordsn=Arrays.copyOf(dxCoordsn,n_c);
            //sort the arrays
            Arrays.sort(dxCoordsp);
            Arrays.sort(dxCoordsn);


            //count if there is multiple occurence of the same value
            double[] dx_simplified_pos=new double[dxCoordsp.length];
            double[] dx_simplified_neg=new double[dxCoordsn.length];
            int[] dx_count_pos=new int[dxCoordsp.length];
            int[] dx_count_neg=new int[dxCoordsn.length];
            dx_simplified_pos[0]=dxCoordsp[0];
            dx_count_pos[0]=1;
            dx_simplified_neg[0]=dxCoordsn[0];
            dx_count_neg[0]=1;
            p_c=0;
            n_c=0;
            //remove multiple occurences for the positive array
            for (int j = 1; j <dxCoordsp.length; j++) {
                if(dx_simplified_pos[p_c]!=dxCoordsp[j]){
                    p_c++;
                }
                dx_simplified_pos[p_c]=dxCoordsp[j];
                dx_count_pos[p_c]++;
            }
            p_c++;
            //remove multiple occurences for the negative array
            for (int j = 1; j <dxCoordsn.length; j++) {
                if(dx_simplified_neg[n_c]!=dxCoordsn[j]){
                    n_c++;
                }
                dx_simplified_neg[n_c]=dxCoordsn[j];
                dx_count_neg[n_c]++;
            }
            n_c++;

            //loop over the arrays to find the right triangles
            int id_p=0;
            int id_n=0;
            while (id_p<p_c && id_n<n_c){
                //check right triangles
                if(dx_simplified_pos[id_p]==dx_simplified_neg[id_n]){
                    count+=dx_count_neg[id_n]*dx_count_pos[id_p];
                    id_p++;
                    id_n++;
                }else if(dx_simplified_pos[id_p]<dx_simplified_neg[id_n]){
                    id_p++;
                }else id_n++;
            }

        }
        System.out.println(count);

    }

}
class Vector{
    //simple 2D vector implementation
    double x;
    double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Vector mul(double c){
        x*=c;
        y*=c;
        return this;
    }

    public double length(){
        return Math.sqrt(x*x+y*y);
    }
    public Vector normalize(){
        double l=length();
        return mul(1.0/l);
    }
    public Vector get_substract(Vector b){
        return new Vector(b.x-x,b.y-y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
