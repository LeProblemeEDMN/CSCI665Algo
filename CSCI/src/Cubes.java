import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Cubes {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int max=sc.nextInt();
        for (int i = 0; i <= max; i++) {
            int pth=(int)Math.round(Math.pow(i,1/3.0));
            if(i==pth*pth*pth){
                System.out.println(i);
            }
        }
    }
}