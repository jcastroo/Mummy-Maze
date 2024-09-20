package searchmethods;

import agent.State;
import java.util.List;

public class GreedyBestFirstSearch extends InformedSearch {

    //f = h
    //prioridade igual ao valor da heuristica
    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

        //TODO
        //Baseado no UniformCostSearch
        for (State state : successors) {
            double g = parent.getG()+state.getAction().getCost();
            if(!frontier.containsState(state)){
                if(!explored.contains(state)) {
                    double h = heuristic.compute(state);
                    Node node = new Node(state, parent, g, h);
                    frontier.add(node);
                }
            }else {
                if(frontier.getNode(state).getG() > g){

                    frontier.removeNode(state);
                    double h = heuristic.compute(state);
                    Node node = new Node(state, parent, g, h);
                    frontier.add(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Greedy best first search";
    }
}