import java.util.Scanner;

public class Palindrome {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int modif=sc.nextInt();
        int ajout=sc.nextInt();
        String empty=sc.nextLine();
        String line=sc.nextLine();
        int[][] array = new int[n][n];
        
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j <= n - i; j++) {
                int k = j + i - 1;
                if (line.charAt(j) == line.charAt(k)) {
                    array[j][k] = array[j + 1][k - 1];
                } else {
                    array[j][k] = Math.min(Math.min(array[j + 1][k] + ajout, array[j][k - 1] + ajout), array[j + 1][k - 1] + modif);
                }
            }
        }
        System.out.println(array[0][n - 1]);
    }

}
