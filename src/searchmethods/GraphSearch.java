package searchmethods;

import agent.Action;
import agent.Problem;
import agent.Solution;
import agent.State;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import utils.NodeCollection;

public abstract class GraphSearch<L extends NodeCollection> implements SearchMethod {

    protected L frontier;
    protected Set<State> explored = new HashSet<>();
    protected Statistics statistics = new Statistics();    
    protected boolean stopped;

    @Override
    public Solution search(Problem problem) {
        statistics.reset();
        stopped = false;
        return graphSearch(problem);
    }

    /*
     function GRAPH-SEARCH(problem) returns a solution, or failure
        initialize the frontier using the initial state of problem
        initialize the explored set to be empty
        while(frontier is not empty)
            remove the first node from the frontier
            if the node contains a goal state then return the corresponding solution
            add the node to the explored set
            expand the node, adding the resulting nodes to the frontier only if
                not in the frontier or explored set
        return failure
     */
    protected Solution graphSearch(Problem problem) {
        // TODO
        Node node = new Node(problem.getInitialState());

        frontier.clear();
        explored.clear();
        frontier.add(node);//no serve para encapsular os states

        while(!frontier.isEmpty() && !stopped){
            Node frontierNode = frontier.remove();
            if(problem.isGoal(frontierNode.getState())){
                return new Solution(problem,frontierNode);
            }

            explored.add(frontierNode.getState());

            List<State> successors = problem.executeActions(frontierNode.getState());

            System.out.println("pai +"+frontierNode.getState().toString());
            addSuccessorsToFrontier(successors, frontierNode);

            computeStatistics(successors.size());
        }

        return null;
    }

    public abstract void addSuccessorsToFrontier(List<State> successors, Node parent);


    protected void computeStatistics(int successorsSize) {
        statistics.numExpandedNodes++; //numero de nos avaliados
        statistics.numGeneratedNodes += successorsSize; //numero de nos avaliados + sucessores
        statistics.maxFrontierSize = Math.max(statistics.maxFrontierSize, frontier.size());
    }
    
    @Override
    public Statistics getStatistics(){
        return statistics;
    }

    @Override
    public void stop() {
        stopped = true;
    }

    @Override
    public boolean hasBeenStopped() {
        return stopped;
    }
}
    