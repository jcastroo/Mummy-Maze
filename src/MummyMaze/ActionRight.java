package MummyMaze;

import agent.Action;

public class ActionRight extends Action<MummyMazeState>{

    public ActionRight(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        if(state.canMoveRight()) {
            state.moveRight();
            state.setAction(this);
        }
    }

    @Override
    public boolean isValid(MummyMazeState state){
        return state.canMoveRight();
    }
}