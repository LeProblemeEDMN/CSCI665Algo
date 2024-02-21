import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MatchTest {
    /*
    3
    1 0 2
    2 1 0
    0 2 1
    1 0 2
    2 1 0
    0 2 1
     */
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=  sc.nextInt();
        int[][] askerPref=new int[n][n];
        int[][] answerPref=new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                askerPref[i][j] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                answerPref[i][j]=sc.nextInt();

        List<int[]>partial_matches=new ArrayList<>();//3*n asker answer idAsk

        int[]initial=new int[3*n];
        for (int i = 0; i < 2*n; i++) {
            initial[i]=-1;
        }
        partial_matches.add(initial);
        List<int[]>matches=new ArrayList<>();//7
        int NB=0;
        while (!partial_matches.isEmpty()){
            NB++;
            int[] pm= partial_matches.get(0);
            boolean complete=true;
            for (int i = 0; i < n; i++) {
                if(pm[i]==-1){
                    complete=false;
                    int[] pm_copy= Arrays.copyOf(pm,3*n);

                    int question=askerPref[i][pm[i+2*n]];
                    if(pm_copy[n+question]==-1){//first match for this answer
                        pm_copy[i+2*n]++;
                        pm_copy[n+question]=i;
                        pm_copy[i]=question;
                    }else{
                        boolean prefere_i=true;
                        for (int j = 0; j < n; j++) {
                            if(answerPref[question][j]==i)break;
                            prefere_i=prefere_i&answerPref[question][j]!=pm[n+question];
                        }
                        //System.out.println("Hopital "+question+" prefere "+i+" à "+pm[n+question]+"? "+prefere_i);
                        if(prefere_i){//check if the new is preffered
                            pm_copy[i+2*n]++;
                            pm_copy[pm[n+question]]=-1;//the old lose is match
                            pm_copy[n+question]=i;
                            pm_copy[i]=question;
                        }else{
                            pm_copy[i+2*n]++;
                        }
                    }
                    //System.out.println(i+" "+Arrays.toString(pm_copy));
                    partial_matches.add(pm_copy);
                }
            }
            partial_matches.remove(pm);
            if(complete){
                System.out.println("MATCH:"+Arrays.toString(Arrays.copyOf(pm,n)));
                matches.add(Arrays.copyOf(pm,n));
            }
        }
        System.err.println("Number of match "+matches.size()+" "+NB);
        for (int i = 0; i < matches.size(); i++) {

            for (int j = i+1; j < matches.size(); j++) {
                int[] m1=matches.get(i);
                int[] m2=matches.get(j);
                boolean test=true;
                for (int k = 0; k < n & test; k++) {
                    test=m1[k]!=m2[k];
                }
                if(test){
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
    }

}

