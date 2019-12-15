package otherAlgo.backtracking;

public class NQueenProblem {

    class Position{
        int row, col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public Position[] solveNQueen(int n) {
        Position[] positions = new Position[n];
        boolean solved = solveNQueenUtil(n, 0, positions);
        if (solved) return positions;
        else return new Position[0];
    }

    private boolean solveNQueenUtil(int n, int row, Position[] positions) {
        if (row == n) return true;
        for (int i = 0; i < n; i++) {
            boolean safe = true;
            for (int j = 0; j < row; j++) {
                if (i == positions[j].col || row + i == positions[j].row + positions[j].col || row - i == positions[j].row - positions[j].col){
                    safe = false;
                    break;
                }
            }
            if (safe){
                positions[row] = new Position(row, i);
                if (solveNQueenUtil(n, row + 1, positions)) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        NQueenProblem nq = new NQueenProblem();
        Position[] pos = nq.solveNQueen(25);
        for (Position p : pos) System.out.println(p);
    }
}
