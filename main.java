// Armando and El
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class main{
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter about of Programers: ");
        int pro = s.nextInt();

        // int[][] programerPreferences = new int[pro][pro];
        // int[][] companyPreferences = new int[pro][pro];
        int[][] programerPreferences = {{1, 2, 0}, {0, 1 , 2}, {2, 1, 0}};
        char[][] companyPreferences = {{'a','b','c'}, {'c','b','a'}, {'b','c','a'}};
        int[][] companyPreferencesTranslated = {{1,2,0},{0,1,2},{2,1,0}};

        char[] alphabet = {'a', 'b' , 'c', 'd', 'e', 'f', 'g'};

        

        // for(int i = 0; i < pro; i++){
        //     System.out.println("Insert preferences for programmer  " + i );
        //     for(int j = 0; j < pro; j++){
        //         programerPreferences [i][j] = s.nextInt();
        //     }
        // }
        // for(int x = 0; x < pro; x++){
        //     System.out.println("Insert preferences for company " + x +"\n please enter letters in integer form ie. a = 0, b = 1, c = 2");
        //     for(int y = 0; y <pro; y++){
        //         companyPreferences [x][y] = s.nextInt();
        //         //companyPreferences[x][y] = s.next().charAt(0);
        //     }
        // }
        s.close();
        
        int[] matches = match(programerPreferences, companyPreferencesTranslated);

        System.out.println("Preferences in Array form \nprogramers:      companies:");
        for (int i = 0; i < pro; i++) {
            int companyId = i + 1;
            System.out.println(alphabet[i] + Arrays.toString(programerPreferences[i]) + "          " + companyId + Arrays.toString(companyPreferences[i]));
        }


        for (int i = 0; i < matches.length; i++) { //int i : matches) {
            if(i % 2 == 0) {
                System.out.println("Match #"+ i/2);
            //     System.out.println(i);
            //     // System.out.println(i +1);
            } //else System.out.println(alphabet[i]);
            System.out.println(matches[i]);
        }
    }
    public static int[][] translator(char[][] companyPreferences, char[] alphabet){
        int[][] companyPreferencesTranslated = new int[companyPreferences.length][companyPreferences[0].length];
        for (int i = 0; i < companyPreferences.length; i++) {
            for (int j = 0; j < companyPreferences[0].length; j++) {
                char c = (char) companyPreferences[i][j];
                companyPreferencesTranslated[i][j] = c;
            }
        }
        return companyPreferencesTranslated;
    }

    static int[] match(int[][] programerPreferences, int[][] companyPreferences) {
        Map<Integer, Integer> temp = new HashMap<>();
        int size = programerPreferences.length;
        boolean[] programersFree = new boolean[size];
        boolean[] companiesFree = new boolean[size];
        Arrays.fill(programersFree, true);
        Arrays.fill(companiesFree, true);
        ArrayList<Integer> stillFree = new ArrayList<>();

        while(stillFree.size() + temp.size() < size){
            for (int i = 0, j = 0; i < companiesFree.length; i++) {
                if (programersFree[i]) {
                    for(; j < size; j++){
                        if (companiesFree[programerPreferences[i][j]]) {
                            temp.put(programerPreferences[i][j], i);
                            programersFree[i] = false;
                            companiesFree[programerPreferences[i][j]] = false;
                            break;
                        } else {
                            int possibleProgramer = temp.get(programerPreferences[i][j]);
                            boolean match = checkMatch(possibleProgramer, i , programerPreferences[i][j], companyPreferences);
                            if( !match){
                                temp.put(programerPreferences[i][j], i);
                                programersFree[i] = false;
                                programersFree[possibleProgramer] = true;
                                break;
                            }
                        }
                    }
                    if (programersFree[i]) {
                        stillFree.add(i);
                    }
                }
            }            
        }
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
            if(companyPreferences[company][i] == possibleProgramer) {
                p = i;
            } else if (companyPreferences[company][i] == possibleProgramer){
                c = 1;
            }
        }
        return p < c;
    }
}
