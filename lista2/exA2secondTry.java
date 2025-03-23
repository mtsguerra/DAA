import java.util.*;

class exA2secondTry {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int nBranches = input.nextInt();
        int nTracks = input.nextInt();
        HashMap<Integer, HashSet<Integer>> branches = new HashMap<>();
        for (int i=1;i<=nBranches;i++) {
            branches.put(i, new HashSet<>());
        }
        for (int i = 0; i < nTracks; i++) {
            int sizeTrack = input.nextInt();
            if (sizeTrack >= 2) {
                int fir = input.nextInt();
                for (int j=1;j<sizeTrack;j++) {
                    int sec = input.nextInt();
                    branches.get(fir).add(sec);
                    fir = sec;
                }
            }
        }
        for (Map.Entry<Integer, HashSet<Integer>> entry : branches.entrySet()) {
            System.out.println(entry.getValue().size());
        }
    }
}