import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

class main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter about of Programers: ");
        int pro = s.nextInt();

        int[][] programerPreferences = new int[pro][pro];
        int[][] companyPreferences = new int[pro][pro];
        char[] alphabet = {'a', 'b' , 'c', 'd', 'e', 'f', 'g'};

        for(int i = 0; i < pro; i++){
            System.out.println("Insert preferences for programmer  " + i );
            for(int j = 0; j < pro; j++){
                programerPreferences [i][j] = s.nextInt();
            }
        }
        for(int x = 0; x < pro; x++){
            System.out.println("Insert preferences for company " + x);
            for(int y = 0; y <pro; y++){
                companyPreferences[x][y] = s.nextInt();
            }
        }

        System.out.println("Preferences in Array form \nprogramers:      companies:");
        for (int i = 0; i < pro; i++) {
            int companyId = i + 1;
            System.out.println(alphabet[i] + Arrays.toString(programerPreferences[i]) + "          " + companyId + Arrays.toString(companyPreferences[i]));
        }
    }
}
