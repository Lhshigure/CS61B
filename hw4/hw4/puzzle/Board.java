package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;
import java.util.Arrays;

public class Board implements WorldState {
    private final int[][] tiles;
    private int N;
    private final int BLANK = 0;
    public Board(int[][] tiles){
        if (tiles == null || tiles[0] == null || tiles.length != tiles[0].length) {
            throw new IllegalArgumentException();
        }
        N = tiles.length;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }
    public int tileAt(int i, int j){
        if(i < 0 || j < 0 || i > N || j > N){
            throw new java.lang.IndexOutOfBoundsException();
        }
        return tiles[i][j];
    }
    public int size(){
        return N;
    }
    public int estimatedDistanceToGoal(){
        return manhattan();
    }
    public int manhattan(){
        int totalDis = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; i++){
                if(tiles[i][j] == 0){
                    continue;
                }
                int targeti = tiles[i][j]-1 / N;
                int targetj = tiles[i][j]-1 % N;
                totalDis += Math.abs(targeti - i) + Math.abs(targetj - j);
            }
        }
        return totalDis;
    }
    public int hamming(){
        int totalDis = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(tiles[i][j] == 0){
                    continue;
                }
                if(tiles[i][j] != i * N + j + 1){
                    totalDis++;
                }
            }
        }
        return totalDis;
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public boolean equals(Object y){
        if(y == this){
            return true;
        }
        if(y == null || this.getClass() != y.getClass()){
            return false;
        }
        Board o = (Board) y;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N ; j ++){
                if(this.tiles[i][j] != o.tiles[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */

    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Returns the string representation of the board.
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
