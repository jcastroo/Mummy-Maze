package agent;

import java.util.List;

public abstract class Problem <S extends State>{

    //TODO

    /* apply all the (valid) actions to some state and return the resulting states
    (executeActions() method);
    • Verify if some state is a goal state (isGoal() method);
    • Calculate the cost of a solution, which is basically a list of actions (computePathCost()
    method).
    */
    protected S initialState;
    protected Heuristic heuristic;
    protected List<Action> actions; //Ações possiveis válidas e inválidas

    public Problem(S initialState, List<Action> actions) {
        this.initialState = initialState;
        this.actions = actions;
    }

    public abstract List<S> executeActions(S state);

    public abstract boolean isGoal(S state);

    protected double computePathCost(List<Action> path){
        double cost = 0;
        for (Action a: path) {
            cost += a.getCost();
        }
        return cost;
    }

    public void setHeuristic(Heuristic heuristic) {
        this.heuristic = heuristic;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    public S getInitialState() {
        return initialState;
    }

}
