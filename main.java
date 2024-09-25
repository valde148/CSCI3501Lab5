// Armando and El
import java.lang.reflect.Array;
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
        //what the program receives: 
        //index equivalents of intended matches
        int[][] companyPreferencesTranslated1 = {{1, 2, 0}, {0, 1 , 2}, {2, 1, 0}};
        int[][] programerPreferencesTranslated1 = {{0,1,2},{2,1,0},{1,2,0}};
        int[] expected = {1, 0, 0, 1, 2, 2};
        int[] returned = match(companyPreferencesTranslated1, programerPreferencesTranslated1);
        System.out.println("test one status:" + (Arrays.equals(expected, returned)));

        //test 2x2
        //int[][] companyPreferences2 = {{1,2},{1,2}};
        //char[][] programerPreferences2 = {{'a','b'},{'b','a'}};
        int[][] companyPreferencesTranslated2 = {{0,1},{0,1}};
        int[][] programerPreferencesTranslated2 = {{0,1},{1,0}};
        int[] expected2 = {0, 0, 1, 1};
        int[] returned2 = match(companyPreferencesTranslated2, programerPreferencesTranslated2);
        System.out.println("test two status:" + (Arrays.equals(expected2, returned2)));

        Scanner s = new Scanner(System.in);
        System.out.println("Enter amount of Programers: ");
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
        for (int i = 0; i < pro; i++) {
            System.out.println("insert programer " + (i+1) + "'s preferences \nplease enter on one line with no spaces or commas");
            String inputString = s.nextLine().toLowerCase();
            char[] input = inputString.toCharArray();
            for (int j = 0; j < input.length; j++) {
                programerPreferencesScanner[i][j] = input[j];
            }
        }
        
        //processes inputs for company preferences
        for (int i = 0; i < pro; i++) {
            System.out.println("insert preferences for company " + alphabet[i] + "\nplease enter each programer id number on a new line");
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
        int[] matches = match(companyPreferencesTranslatedScanner, programerPreferencesTranslated);

        //print statements
        System.out.println("Preferences in Array form \nprogramers:      companies:");
        for (int i = 0; i < pro; i++) {
            int programerId = i + 1;
            // String c = Integer.toString(i + 1);
            System.out.println(programerId + Arrays.toString(programerPreferencesScanner[i]) + "          " + alphabet[i] + Arrays.toString(companyPreferencesScanner[i]));
        }
        for (int i = 0; i < matches.length; i++) {
            if(i % 2 == 0) {
                System.out.println("Match #"+ (i/2 + 1));
                System.out.println(matches[i] +1);
            } else {
                System.out.println(alphabet[matches[i]]);
            }
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
        // array construction
        // temp tracks current pairs
        // programersFree and companiesFree track who is not paired
        // stillFree tracks who is still free
        Map<Integer, Integer> temp = new HashMap<>();
        int size = programerPreferences.length;
        boolean[] programersFree = new boolean[size];
        boolean[] companiesFree = new boolean[size];
        Arrays.fill(programersFree, true);
        Arrays.fill(companiesFree, true);
        ArrayList<Integer> stillFree = new ArrayList<>();

        while(stillFree.size() + temp.size() < size){ //while there is still unpaired people
            for (int i = 0; i < companiesFree.length; i++) { // go through free companies
                if (programersFree[i]) { //if the a programer is free
                    for(int j = 0; j < size; j++){ //for each of the companies they are interested in
                        if (companiesFree[programerPreferences[i][j]]) { //if the company is free
                            temp.put(programerPreferences[i][j], i); // log pair
                            programersFree[i] = false; //mark taken
                            companiesFree[programerPreferences[i][j]] = false; // mark taken
                            break;
                        } else {
                            int possibleProgramer = programerPreferences[i][j]; //else record index of programer
                            boolean match = checkMatch(possibleProgramer, i , programerPreferences[i][j], companyPreferences); //check if possibleProgram has higher interest from company than current hire
                            if(!match){
                                // replace current match with new programer
                                temp.put(programerPreferences[i][j], i);
                                programersFree[i] = false;
                                programersFree[possibleProgramer] = true;
                                break;
                            }
                        }
                    }
                    if (programersFree[i]) {
                        //log that programer needs to go through the process again
                        stillFree.add(i);
                    }
                }
            }            
        }
        // puts matched pairs from the hashMap to an array to be returned
        int[] matchedPairs = new int[2*temp.size()];
            int i = 0;
            for(Map.Entry<Integer, Integer> entry : temp.entrySet()) {
                matchedPairs[i] = entry.getValue();
                i++;
                matchedPairs[i] = entry.getKey();
                i++;
            }
        return matchedPairs;
    }

    static boolean checkMatch(int possibleProgramer, int currentProgramer, int company, int[][] companyPreferences) {
        int p = 0; //index of possible possibleProgramer
        int c = 0; //index of currentProgramer
        for (int i = 0; i < companyPreferences.length; i++) {
            //for each of the programers the company is interested in, record if the possibleProgramer or current hire is higher
            if(companyPreferences[company][i] == possibleProgramer) {
                p = i;
            } else if (companyPreferences[company][i] == currentProgramer){
                c = i;
            }
        }
        return p < c; // respond if the possible programer is of higher interest
    }
}