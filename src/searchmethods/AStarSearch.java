package searchmethods;

import agent.State;
import java.util.List;

public class AStarSearch extends InformedSearch {

    //f = g + h
    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

        //TODO
        for (State state : successors) {
            double g = parent.getG()+state.getAction().getCost();
            if(!frontier.containsState(state)){
                if(!explored.contains(state)) {
                    double h = heuristic.compute(state);
                    Node node = new Node(state, parent, g, g+h);
                    frontier.add(node);
                }
            }else {
                if(frontier.getNode(state).getG() > g){

                    frontier.removeNode(state);
                    double h = heuristic.compute(state);
                    Node node = new Node(state, parent, g, g+h);
                    frontier.add(node);
                }
            }
        }

    }

    @Override
    public String toString() {
        return "A* search";
    }
}
