import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

class main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter about of Programers: ");
        int pro = s.nextInt();

        int[][] programerPreferences = new int[pro][pro];
        char[][] companyPreferences = new char[pro][pro];
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
                companyPreferences[x][y] = s.next().charAt(0);
            }
        }

        System.out.println("Preferences in Array form \nprogramers:      companies:");
        for (int i = 0; i < pro; i++) {
            int companyId = i + 1;
            System.out.println(alphabet[i] + Arrays.toString(programerPreferences[i]) + "          " + companyId + Arrays.toString(companyPreferences[i]));
        }

        matchMaker(programerPreferences, companyPreferences);
    }

    public static void matchMaker(int[][] programerPreferences, char[][] companyPreferences) {
        Boolean[] programerPlaced = new Boolean[programerPreferences[0].length];
        Arrays.fill(programerPlaced, false);
        Boolean[] companyOccupied = new Boolean[programerPreferences[0].length];
        Arrays.fill(companyOccupied, false);
        for (int i = 0; i < programerPreferences[1].length; i++) { //goes through all rounds
            for (int j = 0; j < programerPreferences.length; j++) { //for each applicant
                if (!programerPlaced[j]) { // if applicatnt is not placed
                    int intendedCompanyID = programerPreferences[j][i]; //record intended company
                    int[] applicants = new int[programerPreferences[0].length]; //create array for all other applicants going for same company
                    Arrays.fill(applicants, 0);
                    for (int k = 0; k < applicants.length; k++) {
                        if(!programerPlaced[k] && programerPreferences[j][i] == intendedCompanyID) {
                            // if other applicant is not placed and is interested in same company, mark as interested
                            applicants[k] = 1;
                        }
                    }
                    for (int k = 0; k < applicants.length; k++) {
                        if (applicants[k] == 1 && )
                    }
                }
            }
        }
    }

}
