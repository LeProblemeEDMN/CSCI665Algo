import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MorseVowel {
    private static boolean[] A={true,false};
    private static boolean[] E={true};
    private static boolean[] I={true,true};
    private static boolean[] O={false,false,false};
    private static boolean[] U={true,true,false};
    private static boolean[] Y={false,true,false,false};

    public static boolean match(boolean[] word,boolean[] letter,int begining){
        //check if the letter match the sentence
        boolean match=true;
        for (int i = 0; i < letter.length; i++) {

            match=match & word[begining + i] == letter[i];
        }
        return match;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //input reading
        int n = sc.nextInt();
        String s=sc.next();
        boolean[] point=new boolean[n];
        for (int i = 0; i < n; i++) {
           if(s.charAt(i)=='.')point[i]=true;
        }
        //array initialisation
        int[]counts=new int[4];
        for (int i = 0; i < 4; i++) {
            counts[i]=1;
        }

        for (int i = 0; i < n; i++) {
            int nv=0;
            //reccurence relation
            if(match(point,E,i))nv+=counts[0];
            if(i>0 && match(point,A,i-1))nv+=counts[1];
            if(i>0 && match(point,I,i-1))nv+=counts[1];
            if(i>1 && match(point,O,i-2))nv+=counts[2];
            if(i>1 && match(point,U,i-2))nv+=counts[2];
            if(i>2 && match(point,Y,i-3))nv+=counts[3];
            counts[3]=counts[2];
            counts[2]=counts[1];
            counts[1]=counts[0];
            counts[0]=nv;
        }
        System.out.println(counts[0]);

    }
}
