package interview.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LC353_Design_Snake_Game {

    Queue<Integer> snake;
    int width, height;
    int row, col;
    int[][] food;
    int foodIdx;
    int score;

    public LC353_Design_Snake_Game(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        snake = new LinkedList<>();
        row = 0;
        col = 0;
        foodIdx = 0;
        score = 0;
        snake.add(0);
    }

    public int move(String direction){
        switch (direction){
            case "U": row--; break;
            case "D": row++; break;
            case "L": col--; break;
            case "R": col++; break;
        }
        if (row < 0 || row >= height || col < 0 || col > width) {
            score = -1;
            return score;
        }
        // remove end of snake first, then detect head clash into body
        if (foodIdx < food.length && food[foodIdx][0] == row && food[foodIdx][1] == col) {
            foodIdx++;
            score++;
        }else {
            snake.remove();
        }
        if (snake.contains(row * width + col)) {
            score = -1;
            return score;
        }else{
            snake.add(row * width + col);
        }
        return score;
    }

    private void display() {
        char[][] canvas = new char[height][width];
        if (foodIdx < food.length)
            canvas[food[foodIdx][0]][food[foodIdx][1]] = 'F';
        for (int i : snake) canvas[i / width][i % width] = 'S';
        for (char[] row : canvas){
            for (char c : row)
                System.out.print(c);
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] arsg){
        int[][] f = {{1,2},{0,1},{1,1}};
        LC353_Design_Snake_Game s = new LC353_Design_Snake_Game(3, 2, f);
        if (s.move("R") != -1) s.display();
        if (s.move("D") != -1) s.display();
        if (s.move("R") != -1) s.display();
        if (s.move("U") != -1) s.display();
        if (s.move("L") != -1) s.display();
        if (s.move("D") != -1) s.display();
        if (s.move("R") != -1) s.display();
        if (s.move("U") != -1) s.display();
    }
}
