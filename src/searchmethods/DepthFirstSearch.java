package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class DepthFirstSearch extends GraphSearch<NodeLinkedList> {

    public DepthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    //Graph Search without explored list
    @Override
    protected Solution graphSearch(Problem problem) {

        //TODO
        Node node = new Node(problem.getInitialState());

        frontier.clear();
        //explored.clear();
        frontier.add(node);//no serve para encapsular os states

        while(!frontier.isEmpty() && !stopped){
            Node frontierNode = frontier.remove();
            if(problem.isGoal(frontierNode.getState())){//problem.isGoal(frontierNode.getState())
                //System.out.println("--> OBJETIVO ("+frontierNode.getState().toString()+")");
                return new Solution(problem,frontierNode);
            }

            //explored.add(frontierNode.getState());

            List<State> successors = problem.executeActions(frontierNode.getState());

            //System.out.println("--> Nó origem ("+frontierNode.getState().toString()+")");
            addSuccessorsToFrontier(successors, frontierNode);

            computeStatistics(successors.size());
        }

        return null;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

        //TODO
        for ( State s : successors) {
            if(!frontier.containsState(s)){
                if(!parent.isCycle(s)) {
                    Node node = new Node(s, parent);
                    frontier.addFirst(node);
                }
            }
        }
        //COMPARAR DIFERENCAS
        /*//node.isCycle(state); //verifica se um estado já foi explorado naquele caminho //Utiliza um HashSet para verificar se ele já esta na tabela hash
        for ( State s : successors) {
            if(!frontier.containsState(s)){
                Node node = new Node(s, parent);
                if(!node.isCycle(s)) {
                    frontier.addLast(node);
                }
            }
        }*/
    }

    @Override
    public String toString() {
        return "Depth first search";
    }
}
