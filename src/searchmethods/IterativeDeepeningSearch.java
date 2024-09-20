package searchmethods;

import agent.Problem;
import agent.Solution;
import agent.State;

import java.util.List;

public class IterativeDeepeningSearch extends DepthFirstSearch {
    /*
     * We do not use the code from DepthLimitedSearch because we can optimize
     * so that the algorithm only verifies if a state is a goal if its depth is
     * equal to the limit. Note that given a limit X we are sure not to
     * encounter a solution below this limit because a (failed) limited depth
     * search has already been done. That's why we do not extend this class from
     * DepthLimitedSearch. We extend from DepthFirstSearch so that we don't need
     * to rewrite method insertSuccessorsInFrontier again.
     * After the class, please see a version of the search algorithm without
     * this optimization.
     */

    private int limit;

    @Override
    public Solution search(Problem problem) {
        statistics.reset();
        stopped = false;
        limit = 0;

        //TODO
        /*//MINHA SOLUÇÃO
        Solution s = null;
        while( s == null){
            s = graphSearch(problem);
            limit++;
        }

        return s;*/

        Solution solution;
        do{
            solution = graphSearch(problem);
            limit++;
        }while (solution == null);

        //return search(problem);
        return solution;
    }

    @Override
    protected Solution graphSearch(Problem problem) {

        //TODO
        //validar se é o nó objetivo
        //expandir apenas se o nó pertence ao limite

        Node node = new Node(problem.getInitialState());

        frontier.clear();
        frontier.add(node);//no serve para encapsular os states

        while(!frontier.isEmpty() && !stopped) {
            Node frontierNode = frontier.remove();
            if (frontierNode.getDepth() == limit) {
                if (problem.isGoal(frontierNode.getState())) {
                    return new Solution(problem, frontierNode);
                }
            } else {
                List<State> successors = problem.executeActions(frontierNode.getState());
                super.addSuccessorsToFrontier(successors, frontierNode);
                computeStatistics(successors.size());
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Iterative deepening search";
    }
}


/*
 * 
 public class IterativeDeepeningSearch implements SearchMethod {

    @Override
    public Solution search(Problem problem) {
        DepthLimitedSearch dls = new DepthLimitedSearch();
        Solution solution;
        for (int i = 0;; i++) {
            dls.setLimit(i);
            solution = dls.search(problem);
            if (solution != null) {
                return solution;
            }
        }
    }

    @Override
    public String toString() {
        return "Iterative deepening search";
    }
 *
 */