package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private class Node implements Comparable{
        private Node pre;
        private WorldState worldstate;
        private int move;
        public Node(Node p, WorldState w, int m){
            pre = p;
            worldstate = w;
            move = m;
        }
        @Override
        public int compareTo(Object o) {
            Node other = (Node) o;
            return this.move + worldstate.estimatedDistanceToGoal() - other.move - other.worldstate.estimatedDistanceToGoal();
        }

    }
    private ArrayList<Node> path;
    private int total;
    private void getPath(Node goal){
        path = new ArrayList<>();
        total = goal.move;
        Node p = goal;
        while(p != null){
            path.add(goal);
            p = p.pre;
        }
    }
    public Solver(WorldState initial){
        MinPQ<Node> pq = new MinPQ<>();
        Node start = new Node(null, initial, 0);
        pq.insert(start);
        while(!pq.isEmpty()){
            Node BMS = pq.delMin();
            if(BMS.worldstate.isGoal()){
                getPath(BMS);
                return;
            }else{
                for(WorldState w: BMS.worldstate.neighbors()){
                    if(BMS.pre == null || !w.equals(BMS.pre.worldstate)){
                        pq.insert(new Node(BMS,w,BMS.move + 1));
                    }
                }
            }
        }
    }
    public int moves(){
        return total;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> ret = new ArrayList<>();
        for(int i = total; i >= 0; i--){
            ret.add(path.get(i).worldstate);
        }
        return ret;
    }
}
