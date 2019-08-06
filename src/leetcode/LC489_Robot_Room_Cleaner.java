package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LC489_Robot_Room_Cleaner {

    int[] dr = {0, 1, 0, -1}; // Robot’s initial direction is facing north.
    int[] dc = {1, 0, -1, 0};

    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();
        dfs(0, 0, 0, visited, robot);
    }

    private void dfs(int row, int col, int currDir, Set<String> visited, Robot robot) {
        robot.clean();
        visited.add(row + "-" + col);
        for (int i = 0; i < 4; i++) {
            int nextR = row + dr[currDir];
            int nextC = col + dc[currDir];
            if (!visited.contains(nextR + "-" + nextC) && robot.move()){
                dfs(nextR, nextC, currDir, visited, robot);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            robot.turnRight();
            currDir += 1;
            currDir %= 4;
        }
    }

    public static void main(String[] args) {
        LC489_Robot_Room_Cleaner sol = new LC489_Robot_Room_Cleaner();
    }
}

interface Robot {
    // returns true if next cell is open and robot moves into the cell.
    // returns false if next cell is obstacle and robot stays on the current cell.
    boolean move();

    // Robot will stay on the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    void turnLeft();
    void turnRight();

    // Clean the current cell.
    void clean();
}
