package MummyMaze;

import agent.Action;

public class ActionDown extends Action<MummyMazeState>{

    public ActionDown(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        if(state.canMoveDown()){
            state.moveDown();
            state.setAction(this);
        }

    }

    @Override
    public boolean isValid(MummyMazeState state){
        return state.canMoveDown();
    }
}