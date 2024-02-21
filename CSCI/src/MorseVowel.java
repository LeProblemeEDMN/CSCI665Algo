import java.util.Arrays;
import java.util.Scanner;

public class MorseVowel {
    private static boolean[] A={true,false};
    private static boolean[] E={true};
    private static boolean[] I={true,true};
    private static boolean[] O={false,false,false};
    private static boolean[] U={true,true,false};
    private static boolean[] Y={false,true,false,false};

    public static boolean match(boolean[] word,boolean[] letter,int begining){
        boolean match=true;
        for (int i = 0; i < letter.length; i++) {

            match=match & !(word[begining+i] ^ letter[i]);
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
        int[]counts=new int[4];
        for (int i = 0; i < 4; i++) {
            counts[i]=1;
        }
        //i=0
        /*if(match(point,E,0))counts[3]=1;
        //i=1
        if(match(point,E,1))counts[2]=counts[3];
        if(match(point,A,0))counts[2]+=1;
        if(match(point,I,0))counts[2]+=1;

        //i=2
        if(match(point,E,2))counts[1]=counts[2];
        if(match(point,A,1))counts[1]+=counts[3];
        if(match(point,I,1))counts[1]+=counts[3];
        if(match(point,O,0))counts[1]+=1;
        if(match(point,U,0))counts[1]+=1;

        //i=3
        if(match(point,E,3))counts[0]=counts[1];
        if(match(point,A,2))counts[0]+=counts[2];
        if(match(point,I,2))counts[0]+=counts[2];
        if(match(point,O,1))counts[0]+=counts[3];
        if(match(point,U,1))counts[0]+=counts[3];
        if(match(point,Y,0))counts[1]+=1;*/
        for (int i = 0; i < n; i++) {
            int nv=0;
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
