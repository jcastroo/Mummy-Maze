package MummyMaze;

import agent.Heuristic;

public class HeuristicLowerCost extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state){
        return state.lowercost();
    }

    @Override
    public String toString(){
        return "Lower Cost";
    }
}