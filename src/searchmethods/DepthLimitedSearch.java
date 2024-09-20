package searchmethods;

import agent.State;
import java.util.List;

public class DepthLimitedSearch extends DepthFirstSearch {

    private int limit;

    public DepthLimitedSearch() {
        this(28);
    }

    public DepthLimitedSearch(int limit) {
        this.limit = limit;
    }

    @Override
    public void addSuccessorsToFrontier(List<State> successors, Node parent) {

        //TODO
        if(parent.getDepth() < limit){ // menor pk o nivel é relativo ao nó que origina os filhos do nível 28
            super.addSuccessorsToFrontier(successors,parent);
        }

    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Limited depth first search";
    }
}
