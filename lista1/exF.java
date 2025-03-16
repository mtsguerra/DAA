import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class exF{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int numberOfTeams = input.nextInt();
        int nn = input.nextInt();
        int delay = input.nextInt();
        int blackoutStart = input.nextInt();
        int teamWantsToKnow = input.nextInt();
        input.nextLine();

        // HashMap<Team, HashMap<Problem, int[isRight, time, wrongSubmissions, lastSubmission]>>
        HashMap<Integer,
                HashMap<Integer, int[]>> teams = new HashMap<>();

        for (int i = 0; i< nn; i++){

            int team = input.nextInt();
            int time = input.nextInt();
            int problem = input.nextInt();
            int isRight = input.nextInt();
            input.nextLine();

            teams.putIfAbsent(team, new HashMap<>());
            HashMap<Integer, int[]> currentProblem = teams.get(team);

            currentProblem.putIfAbsent(problem, new int[]{0,0,0,0});
            int[] problemInfo = currentProblem.get(problem);

            if (isRight==1){
                problemInfo[0]=1;
                problemInfo[1]=time;
            }
            else{
                problemInfo[2]++;
            }
            problemInfo[3]=time;
        }

        int blackoutSubs = input.nextInt();
        input.nextLine();

        int vTotalTime =0;

        for (int i = 0; i<blackoutSubs; i++){
            int tmp = input.nextInt();
            if (i==blackoutSubs-1){
                input.nextLine();
                vTotalTime+=tmp;
                break;
            }
            vTotalTime+=20*60;
            input.nextLine();
        }

        HashMap<Integer, int[]> vTeam = teams.get(teamWantsToKnow);

        for (Map.Entry<Integer, int[]> entry : vTeam.entrySet()) {
            int[] problemInfo = entry.getValue();
            vTotalTime += problemInfo[1] + 20 * problemInfo[2] * 60;
        }

        for (int i=0;i<=numberOfTeams;i++){
            if (!teams.containsKey(i) || i==teamWantsToKnow) continue;
            HashMap<Integer, int[]> current = teams.get(i);
            int t = getT(current, delay, blackoutStart);
            if (t < vTotalTime){
                System.out.println("Nao sabemos");
                return;
            }
        }
        System.out.println("Vencemos");
    }

    private static int getT(HashMap<Integer, int[]> current, int delay, int blackoutStart) {
        int solved =0;
        int totalTime=0;
        int lastSubmissionTime = 0;
        for (int[] problemInfo : current.values()) {
            if (problemInfo[0]==1) solved++;
            totalTime += problemInfo[1] + 20 * problemInfo[2] * 60;
            lastSubmissionTime = Math.max(lastSubmissionTime, problemInfo[3]);
        }
        int currentTime = Math.max(lastSubmissionTime+delay, blackoutStart);
        while (solved < 7 && currentTime <= 14400) {
            solved++;
            totalTime+=currentTime;
            currentTime+=delay;
        }
        return totalTime;
    }
}