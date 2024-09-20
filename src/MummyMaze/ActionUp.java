package MummyMaze;

import agent.Action;

public class ActionUp extends Action<MummyMazeState>{

    public ActionUp(){
        super(1);
    }

    @Override
    public void execute(MummyMazeState state){
        if(state.canMoveUp()) {
            state.moveUp(); //manda o estado modificar-se movendo a vazia para cimae define esta ação como sendo a ação que deu origem ao estado
            state.setAction(this);
        }
    }

    @Override
    public boolean isValid(MummyMazeState state){
        return state.canMoveUp(); //pergunta ao estado se a peça vazia pode andar para cima neste caso
    }
}