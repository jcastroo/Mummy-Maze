package MummyMaze.Atacante;

import MummyMaze.MummyMazeState;

public class MumiaBranca extends AgenteAtacante{
    public MumiaBranca(MummyMazeState state, int row, int coll,int poder) {
        super(state,row,coll,poder,'M');
    }

    public boolean jogar() {
        for (int i = 0; i < 2; i++) {
            aproximaPorColunas();
            atacanteMorre();
            state.setMatrix(state.getMatrix());
            if (state.getColumnH()==atacanteColl && state.getLineH()== atacanteRow){
                return true;
            }
        }
        return false;
    }

    private void aproximaPorColunas(){
        if(state.getColumnH() > atacanteColl){
            if (canMoveRight(state.getMatrix())) {
                moveRight(state.getMatrix(), 'M');
            }
            else{
                aproximaPorLinhas();
            }
        }else if(state.getColumnH() < atacanteColl) {
            if (canMoveLeft(state.getMatrix()) ) {
                moveLeft(state.getMatrix(), 'M');
            }
            else{
                aproximaPorLinhas();
            }
        }else if(state.getColumnH() == atacanteColl){
            aproximaPorLinhas();
        }
        //aproximaPorLinhas();
    }
    private void aproximaPorLinhas(){
        if(state.getLineH() > atacanteRow){
            if (canMoveDown(state.getMatrix())) {
                moveDown(state.getMatrix(), 'M');
            }
        }else if(state.getLineH() < atacanteRow) {
            if (canMoveUp(state.getMatrix())) {
                moveUp(state.getMatrix(), 'M');
            }
        }
    }

}
