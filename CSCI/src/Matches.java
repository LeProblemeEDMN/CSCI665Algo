import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Matches {
    /*
    3
    1 0 2
    2 1 0
    0 2 1
    1 0 2
    2 1 0
    0 2 1
     */

    public static int[][] askerPref,answerPref,rev_askerPref,rev_answerPref;

    public static void compute_stable_match(int n,int idA,int[] parent,List<int[]>matches){
        //Branch and Cut function compute all the matching possible by branching over the possible matching.

        //if the matching is complete add it to the stable matches list
        if(idA>=n){
            //System.out.println(Arrays.toString(Arrays.copyOf(parent,n)));
            matches.add(parent);
            return;
        }
        for (int b = 0; b < n; b++) {
            if(parent[n+b]>=0)continue;//already matched
            boolean conflict=false;//true if we can cut the branch
            for (int c = 0; c < idA; c++) {
                //check if the pairs can be exchanged
                int d=parent[c];
                //try stability of couple (a,b) (the new one) and (c,d)
                if((rev_askerPref[idA][d]<rev_askerPref[idA][b] && rev_answerPref[d][idA]<rev_answerPref[d][c])||
                        (rev_answerPref[b][c]<rev_answerPref[b][idA] && rev_askerPref[c][b]<rev_askerPref[c][d])){
                    conflict=true;
                    break;
                }
            }
            //if no conflict explore the tree.
            if(!conflict){
                int child[]=Arrays.copyOf(parent,2*n);
                child[idA]=b;
                child[n+b]=idA;
                compute_stable_match(n,idA+1,child,matches);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=  sc.nextInt();
        askerPref=new int[n][n];
        answerPref=new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                askerPref[i][j] = sc.nextInt();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                answerPref[i][j]=sc.nextInt();


        //compute the reverse order the case (i,j) contains the order of the j element for i preferences.
        rev_askerPref=new int[n][n];
        rev_answerPref=new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                rev_askerPref[i][askerPref[i][j]] = j;
                rev_answerPref[i][answerPref[i][j]] = j;
            }

        int[]initial=new int[2*n];
        for (int i = 0; i < 2*n; i++) {
            initial[i]=-1;
        }

        List<int[]>matches=new ArrayList<>();//7

        compute_stable_match(n,0,initial,matches);//compute all the stable matches
        //System.err.println("Number of match "+matches.size());
        //check if there exists two stable matches that have no pairs in commun
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

