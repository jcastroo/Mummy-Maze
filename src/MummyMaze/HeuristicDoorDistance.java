package MummyMaze;

import agent.Heuristic;

public class HeuristicDoorDistance extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state){
        return state.computeDoorDistance();
    }

    @Override
    public String toString(){
        return "Door distance to final position";
    }
}