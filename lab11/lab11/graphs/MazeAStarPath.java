package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.PriorityQueue;
import java.util.Queue;


/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;


    private class Node implements Comparable{
        private  int v;
        private  int priority;
        public Node (int v, int dist){
            this.v = v;
            this.priority = dist + h(v);
        }
        @Override
        public int compareTo(Object o){
            Node other = (Node) o;
            return this.priority - other.priority;
        }

    }


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
       return Math.abs(maze.toX(t) - maze.toX(v)) + Math.abs(maze.toY(t) - maze.toY(v));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        if(s == t){
            targetFound = true;
            return;
        }
        Queue<Node> pq = new PriorityQueue<>();
        Node source = new Node(s, 0);
        distTo[s] = 0;
        pq.add(source);
        for(int i = 1; i < maze.V(); i++){
            pq.add(new Node(i, Integer.MAX_VALUE));
            distTo[i] = Integer.MAX_VALUE;
        }
        while(!pq.isEmpty()){
            Node curr = pq.remove();
            marked[curr.v] = true;
            announce();
            if(curr.v == t){
                return;
            }
            for(int adj: maze.adj(curr.v)){
                if(distTo[curr.v] + 1 < distTo[adj]){
                    distTo[adj] = distTo[curr.v] + 1;
                    edgeTo[adj] = curr.v;
                }
                if(!marked[adj]){
                    pq.add(new Node(adj, distTo[adj]));
                }
            }
        }
    }


    @Override
    public void solve() {
        astar(s);
    }

}

