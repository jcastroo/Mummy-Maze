package MummyMaze.Atacante;

import MummyMaze.MummyMazeState;

public class MumiaVermelha extends AgenteAtacante{
    public MumiaVermelha(MummyMazeState state, int row, int coll,int poder) {
        super(state,row,coll,poder,'V');
    }

    public boolean jogar() {
        for (int i = 0; i < 2; i++) {
            aproximaPorLinhas();
            atacanteMorre();
            if (state.getColumnH()==atacanteColl && state.getLineH()== atacanteRow){
                return true;
            }

        }
        return false;
    }

    private void aproximaPorColunas(){
        if(state.getColumnH() > atacanteColl){
            if (canMoveRight(state.getMatrix())) {
                moveRight(state.getMatrix(), 'V');
            }
        }else if(state.getColumnH() < atacanteColl) {
            if (canMoveLeft(state.getMatrix())) {
                moveLeft(state.getMatrix(), 'V');
            }
        }
    }
    private void aproximaPorLinhas(){
        if(state.getLineH() > atacanteRow){
            if (canMoveDown(state.getMatrix())) {
                moveDown(state.getMatrix(), 'V');
            }else {
                aproximaPorColunas();
            }
        }else if(state.getLineH() < atacanteRow) {
            if (canMoveUp(state.getMatrix()) ) {
                moveUp(state.getMatrix(), 'V');
            }else {
                aproximaPorColunas();
            }
        }else if(state.getLineH() == atacanteRow){
            aproximaPorColunas();
        }
    }
}
