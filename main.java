// Armando and El
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter number of Programmers & Companies: ");
        int pro = s.nextInt();

        int[][] programerPreferences = new int[pro][pro];
        int[][] companyPreferences = new int[pro][pro];
        char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g'}; //these are the lables of the companies

        // the input preferences for programmer
        for(int i = 0; i < pro; i++){
            System.out.println("put pref for programmer " + (i+1) + " (enter " + pro + " values):");
            for(int j = 0; j < pro; j++){
                programerPreferences[i][j] = s.nextInt();
            }
        }

        // the input preferences for companies
        for(int x = 0; x < pro; x++){
            System.out.println("put pref for company " + alphabet[x] + " (enter " + pro + " values, where a=0, b=1, c=2, etc.):");
            for(int y = 0; y < pro; y++){
                companyPreferences[x][y] = s.nextInt();
            }
        }
        s.close();

        int[] matches = match(programerPreferences, companyPreferences);

        // displaying the prefrences
        for (int i = 0; i < pro; i++) {
            System.out.println((i+1) + Arrays.toString(programerPreferences[i]) + "          " + alphabet[i] + Arrays.toString(companyPreferences[i]));
        }

        // thiw is the matching results
        System.out.println("\nMatching results:");
        for (int i = 0; i < matches.length; i++) {
            System.out.println("Programmer " + (matches[i] + 1) + " matched with Company " + alphabet[i]);
        }
    }

    // the matching algorithm
    static int[] match(int[][] programerPreferences, int[][] companyPreferences) {
        int size = programerPreferences.length;
        Map<Integer, Integer> matches = new HashMap<>();
        boolean[] programersFree = new boolean[size];
        boolean[] companiesFree = new boolean[size];
        Arrays.fill(programersFree, true);
        Arrays.fill(companiesFree, true);

        while (matches.size() < size) {
            for (int i = 0; i < size; i++) {
                if (programersFree[i]) {
                    //the programmer i ask to their next preferred company
                    for (int j = 0; j < size; j++) {
                        int company = programerPreferences[i][j];
                        if (companiesFree[company]) {
                            // the company if avalible, they accept the proposal
                            matches.put(company, i);
                            programersFree[i] = false;
                            companiesFree[company] = false;
                            break;
                        } else {
                            // the company already has a programmer, they will see if they prefer the new one
                            int currentProgrammer = matches.get(company);
                            boolean preferNew = checkMatch(currentProgrammer, i, company, companyPreferences);
                            if (preferNew) {
                                //the company switches to the new programmer if it is a better choice
                                matches.put(company, i);
                                programersFree[i] = false;
                                programersFree[currentProgrammer] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        int[] matchedPairs = new int[size];
        for (Map.Entry<Integer, Integer> entry : matches.entrySet()) {
            matchedPairs[entry.getKey()] = entry.getValue();
        }
        return matchedPairs;
    }

    // it will check if the company wants the new programmer over the last pick
    static boolean checkMatch(int currentProgrammer, int newProgrammer, int company, int[][] companyPreferences) {
        for (int i = 0; i < companyPreferences[company].length; i++) {
            if (companyPreferences[company][i] == newProgrammer) {
                return true; // new programmer is picked over the last one
            }
            if (companyPreferences[company][i] == currentProgrammer) {
                return false; // if the current programmer is picked
            }
        }
        return false;
    }
}
