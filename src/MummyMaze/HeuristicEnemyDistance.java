package MummyMaze;

import agent.Heuristic;

public class HeuristicEnemyDistance extends Heuristic<MummyMazeProblem, MummyMazeState> {

    @Override
    public double compute(MummyMazeState state){
        return state.computeEnemyDistance();
    }

    @Override
    public String toString(){
        return "Enemy Distance";
    }
}