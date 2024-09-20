package MummyMaze;

import agent.Action;

public class ActionLeft extends Action<MummyMazeState>{

    public ActionLeft(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        if(state.canMoveLeft()) {
            state.moveLeft();
            state.setAction(this);
        }
    }

    @Override
    public boolean isValid(MummyMazeState state){
        return state.canMoveLeft();
    }
}
