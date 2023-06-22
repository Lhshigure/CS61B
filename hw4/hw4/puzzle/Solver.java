package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.List;

public class Solver {
   private class SearchNode implements Comparable<SearchNode>{
       private WorldState state;
       private int moves;
       private SearchNode pre;
       public SearchNode(WorldState state, int moves, SearchNode pre){
           this.state = state;
           this.moves = moves;
           this.pre = pre;
       }
       public WorldState getState(){
           return state;
       }
       public int getMoves(){
           return moves;
       }
        public SearchNode getPre(){
           return pre;
        }

       @Override
       public int compareTo(SearchNode o) {
           return this.state.estimatedDistanceToGoal() + this.moves - o.state.estimatedDistanceToGoal() - o.moves;
       }
   }
   private MinPQ<SearchNode> openNodes = new MinPQ<>();
   private List<WorldState> bestSolution;
   private int totMoves;

   private void getAnswer(SearchNode goal){
       totMoves = goal.moves;
       bestSolution = new ArrayList<>();
       SearchNode p = goal;
       while(p != null){
           bestSolution.add(p.state);
           p = p.pre;
       }
   }
    public Solver(WorldState initial){
        openNodes.insert(new SearchNode(initial, 0, null));
        while(true){
            SearchNode node = openNodes.delMin();
            if(node.state.isGoal()){
                getAnswer(node);
            }else {
                for(WorldState neighbour : node.state.neighbors()){
                    if(node.pre == null || !neighbour.equals(node.pre.state)){
                        openNodes.insert(new SearchNode(neighbour, node.moves + 1, node));
                    }
                }
            }
        }


    }
    public int moves(){
        return totMoves;
    }
    public Iterable<WorldState> solution(){
        List<WorldState> ret = new ArrayList<>();
        for(int i = totMoves; i>-0; i--){
            ret.add(bestSolution.get(i));
        }
        return ret;
   }
}
