import java.util.*;

class exE2{

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] board = new int[9][9];
        for (int i=8;i>=0;i--){
            for (int j=0;j<9;j++){
                board[i][j] = input.nextInt();
            }
        }
        int startX = input.nextInt() - 1;
        int startY = input.nextInt() - 1;
        int targetX = input.nextInt() - 1;
        int targetY = input.nextInt() - 1;
        int color = board[startX][startY];
        if (!isPossible(board, startX, startY, targetX, targetY)) {
            System.out.println(0);
            return;
        }
        else{
            board[startX][startY] = 0;
            board[targetX][targetY] = color;
            int score = checksForScore(board, color, targetX, targetY);
            System.out.println(score);
        }

    }

    private static int checksForScore (int[][] board, int color,
                                       int targetX, int targetY){
        int totalScore = 0;
        int currentScore = 0;
        int tempX;
        int tempY;



        // checks for horizontal
        tempY = targetY-1;
        while (tempY >= 0
                &&  board[targetX][tempY] == color){
            currentScore++;
            tempY--;
        }
        tempY = targetY+1;
        while (tempY < 9
                && board[targetX][tempY] == color){
            currentScore++;
            tempY++;
        }
        if (currentScore+1 >= 5) totalScore+= currentScore;




        // checks for vertical
        currentScore = 0;
        tempX = targetX-1;
        while (tempX >= 0
                && board[tempX][targetY] == color){
            currentScore++;
            tempX--;
        }
        tempX = targetX+1;
        while (tempX < 9
                && board[tempX][targetY] == color){
            currentScore++;
            tempX++;
        }
        if (currentScore+1 >= 5) totalScore+= currentScore;





        // checks for main diagonal
        currentScore = 0;
        tempX = targetX-1;
        tempY = targetY-1;
        while (tempX >= 0 && tempY >= 0
                && board[tempX][tempY] == color){
            currentScore++;
            tempX--;
            tempY--;
        }
        tempX = targetX+1;
        tempY = targetY+1;
        while (tempX < 9 && tempY < 9
                && board[tempX][tempY] == color){
            currentScore++;
            tempX++;
            tempY++;
        }
        if (currentScore+1 >= 5) totalScore+= currentScore;


        // checks for secondary diagonal
        currentScore = 0;
        tempX = targetX-1;
        tempY = targetY+1;
        while (tempX >= 0 && tempY < 9
                && board[tempX][tempY] == color){
            currentScore++;
            tempX--;
            tempY++;
        }
        tempX = targetX+1;
        tempY = targetY-1;
        while (tempX < 9 && tempY >= 0
                && board[tempX][tempY] == color){
            currentScore++;
            tempX++;
            tempY--;
        }
        if (currentScore+1 >= 5) totalScore+= currentScore;



        return totalScore > 0 ? totalScore+1 : 0;

    }

    private static boolean isPossible(int[][] board, int startX, int startY,
                                      int xTarget, int yTarget){
        boolean[][] visited = new boolean[9][9];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startX, startY});

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        while (!q.isEmpty()){
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];

            if (x == xTarget && y == yTarget){
                return true;
            }
            for (int[] direction : directions){
                int nx = x + direction[0];
                int ny = y + direction[1];

                if (nx >= 0 && nx < 9 && ny >= 0 && ny < 9
                        && !visited[nx][ny]
                        && board[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }
            }
        }

        return false;
    }

}