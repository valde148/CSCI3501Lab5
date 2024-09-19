import java.util.Scanner;

class main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter about of Progamers: ");
        int pro = s.nextInt();

        int[][] progamerprefernces = new int[pro][pro];
        int[][] companyPrefernces = new int[pro][pro];

        for(int i = 0; i < pro; i++){
            System.out.println("Insert prefrences for programmer  " + i );
            for(int j = 0; j < pro; j++){
                progamerprefernces [i][j] = s.nextInt();
            }
        }
        for(int x = 0; x < pro; x++){
            System.out.println("Insert prefrences for company " + i);
            for(int y = 0; y <pro; y++){
                companyPrefernces[x][y] = s.nextInt();
            }
        }

        


    }
}
