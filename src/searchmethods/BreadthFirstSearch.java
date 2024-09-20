package searchmethods;

import agent.State;
import java.util.List;
import utils.NodeLinkedList;

public class BreadthFirstSearch extends GraphSearch<NodeLinkedList> {

    public BreadthFirstSearch() {
        frontier = new NodeLinkedList();
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

        //TODO
        //para cada nó sucessor
        for ( State s : successors) {
            //se não estiver na fronteira nem nos explorados
            if(!frontier.containsState(s)){
                if(!explored.contains(s)) {
                    //adicionar ao fim da lista ligada
                    Node node = new Node(s, parent);
                    System.out.println("filho +"+s);
                    frontier.addLast(node);
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Breadth first search";
    }
}