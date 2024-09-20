package MummyMaze;

import agent.Action;

public class ActionStop extends Action<MummyMazeState> {
    public ActionStop() {
        super(2);
    }

    @Override
    public void execute(MummyMazeState state) {
        state.moveStop(); //manda o estado modificar-se movendo a vazia para cimae define esta ação como sendo a ação que deu origem ao estado
        state.setAction(this);
    }

    @Override
    public boolean isValid(MummyMazeState state) {
        return true;
    }
}
