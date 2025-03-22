import java.util.*;

class exDsecondTry{

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nTypes = input.nextInt();
        int numberOfChairs = 0;
        HashMap<Integer,Integer> map = new HashMap<>();
        for (int i=0;i<nTypes;i++){
            int type = input.nextInt();
            int nChairs = input.nextInt();
            numberOfChairs += nChairs;
            if (nChairs > 0) map.put(type,nChairs);
        }
        int nPeople = input.nextInt();
        int pepopleStanding =0;
        for (int i=0;i<nPeople;i++){
            int numberOfPreferences = input.nextInt();
            if (numberOfPreferences==0){
                pepopleStanding++;
                continue;
            }
            int [] preferences = new int[numberOfPreferences];
            for (int j=0;j<numberOfPreferences;j++){
                preferences[j] = input.nextInt();
            }
            boolean found = false;
            for (int pref : preferences){
                if (map.containsKey(pref)){
                    numberOfChairs--;
                    if (map.get(pref)==1) map.remove(pref);
                    else map.put(pref,map.get(pref)-1);
                    found=true;
                    break;
                }
            }
            if (!found) pepopleStanding++;
        }
        System.out.println(pepopleStanding);
        System.out.println(numberOfChairs);
    }
}