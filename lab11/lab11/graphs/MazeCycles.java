package lab11.graphs;
import edu.princeton.cs.algs4.In;

import java.util.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private boolean cycleFound = false;
    private  Maze maze;
    private int nodeTo[]; // 用来存放当前node的parent， 类似edgTo

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1,1);
        distTo[s] = 0;
        edgeTo[s] = s;
        nodeTo = new int[maze.N()*maze.N()];
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        // 假设最初0的parent是-1
        solve(-1,0);

    }

    // Helper methods go here
    public void solve(int u, int v){
        marked[v] = true;
        announce();
        for(int w: maze.adj(v)){
            if(!marked[w]) {
                nodeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                solve(v, w);
            } else if (u != w ) {
                // finding cycle
                edgeTo[w] = v;
                announce();
                //往前找当前node的parent，直到找到这个已被marked的node
                for(int x = v; x != w; x = nodeTo[x]){
                    //找到cycle后开始画线
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
                cycleFound = true;
            }
            if(cycleFound){
                return;
            }
        }
    }
}

