package searchmethods;

import agent.State;
import java.util.List;
import utils.NodePriorityQueue;

public class UniformCostSearch extends GraphSearch<NodePriorityQueue> {

    public UniformCostSearch(){
        frontier = new NodePriorityQueue();
    }    
    
    // f = g
    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) { //adiciona os nos na fronteira de acordo com o custo

        //TODO

        //para cads no sucessor
        for (State state : successors) {
        //    obter o custo do nó (g)
            double g = parent.getG()+state.getAction().getCost();
        //    se o nó não estiver na fronteira
            if(!frontier.containsState(state)){
        //      se o no não estiver nos explorados
                if(!explored.contains(state)) {
        //          acrescenta o nó á fronteira com f = g
                    Node node = new Node(state, parent, g, g);
                    frontier.add(node);
                }
            }else {

        //    se o nó estiver na fronteira
                if(frontier.getNode(state).getG() > g){
        //      remover o no que esta na fronteira e
                    frontier.removeNode(state);
        //          acrescentar o no à fronteira (com f = g)
                    Node node = new Node(state, parent, g, g);
                    frontier.add(node);
                }
            }
        }


    }

    @Override
    public String toString() {
        return "Uniform cost search";
    }
}
