import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
/*

1 10
5 2
9 4
7 7
10 5
6 9
3 3
8 6
4 1
2 8


1 10 / 9 4 / 8 6 / 5 2 /
 */
public class PickHalf {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N=sc.nextInt();
        int[][] cards=new int[N][2];
        int[][] id_pairs=new int[N][2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 2; j++) {
                cards[i][j] = sc.nextInt()-1;
                if (id_pairs[cards[i][j]][0] == 0) id_pairs[cards[i][j]][0] = 1+i;
                else id_pairs[cards[i][j]][1] = 1+i;
            }
        }
        boolean[] visited=new boolean[N];
        int idFirst=0;
        while (idFirst<N ){

           int[] card=cards[id_pairs[idFirst][0]-1];

           int[] card2=cards[id_pairs[idFirst][1]-1];
           int card_other=card[0]==idFirst?card[1]:card[0];
           int last=idFirst;
            if(last==card_other){
                System.out.println("NO");
                return;
            }
           int current=card2[0]==idFirst?card2[1]:card2[0];

           visited[idFirst]=true;
           visited[card_other]=true;

           while(current!=card_other){

               card=cards[id_pairs[current][0]-1];
               if(card[0]==last || card[1]==last){
                   card=cards[id_pairs[current][1]-1];
               }
               visited[card[0]]=true;
               visited[card[1]]=true;

               int new_other=card[0]==current?card[1]:card[0];
               if(new_other==card_other){
                   System.out.println("NO");
                   return;
               }

               card2=cards[id_pairs[new_other][0]-1];
               if(card2[0]==current || card2[1]==current){
                   card2=cards[id_pairs[new_other][1]-1];
               }

               current=card2[0]==new_other?card2[1]:card2[0];
               last=new_other;
           }


           idFirst=0;
           while (idFirst<N && visited[idFirst])idFirst++;
        }
        System.out.println("YES");
    }
}
