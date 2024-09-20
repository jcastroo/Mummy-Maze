package agent;

import java.util.LinkedList;
import java.util.List;

import searchmethods.Node;

public class Solution {
    private final Problem problem;
    private final LinkedList<Action> actions; //lista de acoes que resultam num estado final no fim de executar todas
    private State estadoFinalSearch;

    public Solution(Problem problem, Node goalNode){
        this.estadoFinalSearch = goalNode.getState();
        this.problem = problem;
        Node node = goalNode;
        actions = new LinkedList<>();
        while(node.getParent() != null){
            actions.addFirst(node.getState().getAction());
            node = node.getParent();
        }        
    }

    public double getCost(){
        return problem.computePathCost(actions);
    }

    public Problem getProblem() {
        return problem;
    }

    public State getEstadoFinalSearch() {
        return estadoFinalSearch;
    }

    public List<Action> getActions(){
        return actions;
    }
}