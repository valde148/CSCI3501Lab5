// Armando and El
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class main{
    public static void main(String[] args){
        char[] alphabet = {'a','b','c','d','e','f','g','h','i','j'};

        //test 3x3
        //user entries:
        //int[][] companyPreferences1 = {{2, 3, 1}, {1, 2, 3}, {3, 2, 1}}; //this is what is entered on the user side
        //char[][] programerPreferences1 = {{'a','b','c'}, {'c','b','a'}, {'b','c','a'}}; //this is what is entered on the user side
        //what the program receives: index equivalents of intended matches
        int[][] programerPreferencesTranslated1 = {{1, 2, 0}, {0, 1 , 2}, {2, 1, 0}};
        int[][] companyPreferencesTranslated1 = {{0,1,2},{2,1,0},{1,2,0}};
        int[] expected = {1,0,2}; //programer with id one will match with company b, 2, with a, three with c
        int[] returned = match(programerPreferencesTranslated1, companyPreferencesTranslated1);

        System.out.println("test one status:" + (Arrays.equals(expected, returned)));

        //test 2x2
        //int[][] companyPreferences2 = {{1,2},{1,2}};
        //char[][] programerPreferences2 = {{'a','b'},{'b','a'}};
        int[][] companyPreferencesTranslated2 = {{0,1},{0,1}};
        int[][] programerPreferencesTranslated2 = {{0,1},{1,0}};
        int[] expected2 = {0, 1};
        int[] returned2 = match(programerPreferencesTranslated2, companyPreferencesTranslated2);
        System.out.println("test two status:" + (Arrays.equals(expected2, returned2)));

        // Armando Test 1
        int[][] programerPreferencesTranslated3 = {{0,1,2},{1,0,2},{2,0,1}};
        int[][] companyPreferencesTranslated3 = {{1,0,2},{0,1,2},{2,1,0}};
        int[] expected3 = {0,1,2};
        int[] returned3 = match(programerPreferencesTranslated3, companyPreferencesTranslated3);
        System.out.println("test three status: " + (Arrays.equals(expected3,returned3)));

        //Armando test 2
        int[][] programerPreferencesTranslated4 = {{0,1,2},{2,1,0},{1,2,0}};
        int[][] companyPreferencesTranslated4 = {{1,2,0},{0,1,2},{2,0,1}};
        int[] expected4 = {0,2,1};
        int[] returned4 = match(programerPreferencesTranslated4, companyPreferencesTranslated4);
        System.out.println("test four status: " + (Arrays.equals(expected4,returned4)));
        // Ends tests

        Scanner s = new Scanner(System.in);
        System.out.println("Enter amount of Programers & Companies: ");
        int pro = s.nextInt();

        //array constructors
        char[][] programerPreferencesScanner = new char[pro][pro];
        int[][] companyPreferencesScanner = new int[pro][pro];
        int[][] companyPreferencesTranslatedScanner = new int[pro][pro];

        // processes first input for programer preferences
        String inputString1 = s.nextLine().toLowerCase();
        char[] input1 = inputString1.toCharArray();
        for (int j = 0; j < input1.length; j++) {
                programerPreferencesScanner[1][j] = input1[j];
        }
        //processes rest of inputs
        System.out.println("Please enter programer preferences by typing each of the company Id's (letters) on one line with no spaces or commas");
        for (int i = 0; i < pro; i++) {
            System.out.println("insert programer " + (i+1) + "'s preferences");
            String inputString = s.nextLine().toLowerCase();
            char[] input = inputString.toCharArray();
            for (int j = 0; j < input.length; j++) {
                programerPreferencesScanner[i][j] = input[j];
            }
        }
        
        //processes inputs for company preferences
        System.out.println("Please enter company preferences by typing each of the programer ID's (number) on a new line");
        for (int i = 0; i < pro; i++) {
            System.out.println("insert preferences for company " + alphabet[i]);
            for (int j = 0; j < pro; j++) {
                companyPreferencesScanner[i][j] = s.nextInt();
                companyPreferencesTranslatedScanner[i][j] = companyPreferencesScanner[i][j] -1;
            }
        }
        //ends scanner
        s.close();
        //translates user imputed programer preferences to something our method understands
        int[][] programerPreferencesTranslated = translator(programerPreferencesScanner);
        //calls match method
        int[] matches = match(programerPreferencesTranslated, companyPreferencesTranslatedScanner);

        //print statements
        System.out.println("Preferences in Array form \nprogramers:      companies:");
        for (int i = 0; i < pro; i++) {
            int programerId = i + 1;
            // String c = Integer.toString(i + 1);
            System.out.println(programerId + Arrays.toString(programerPreferencesScanner[i]) + "          " + alphabet[i] + Arrays.toString(companyPreferencesScanner[i]));
        }
        System.out.println("\nMatching results:");
        for (int i = 0; i < matches.length; i++) {
            System.out.println("Programmer " + (matches[i] + 1) + " matched with Company " + alphabet[i]);
        }
    }

    public static int[][] translator(char[][] companyPreferences){
        //takes character array and converts it to integers where a = 0, b = 1, c = 2, etc...
        // logic to make everything lowercase is handled in scanner
        int[][] companyPreferencesTranslated = new int[companyPreferences.length][companyPreferences[0].length];
        for (int i = 0; i < companyPreferences.length; i++) {
            for (int j = 0; j < companyPreferences[0].length; j++) {
                char c = companyPreferences[i][j];
                int n = c - 97;
                companyPreferencesTranslated[i][j] = n;
            }
        }
        return companyPreferencesTranslated;
    }

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