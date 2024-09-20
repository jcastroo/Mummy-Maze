package MummyMaze.Atacante;

import MummyMaze.MummyMazeState;

public class Escorpiao extends AgenteAtacante{

    public Escorpiao(MummyMazeState state, int row, int coll,int poder) {
        super(state,row,coll,poder,'E');
    }

    public boolean jogar() {

        if(state.getColumnH() > atacanteColl){
            if(canMoveRight(state.getMatrix())) {
                moveRight(state.getMatrix(), 'E');
            }else {
                if(state.getLineH() > atacanteRow) {
                    if (canMoveDown(state.getMatrix())) {
                        moveDown(state.getMatrix(), 'E');
                    }
                }else
                if(state.getLineH() < atacanteRow) {
                    if (canMoveUp(state.getMatrix())) {
                        moveUp(state.getMatrix(), 'E');
                    }
                }
            }
            ;
        }else
        if(state.getColumnH() < atacanteColl){
            if(canMoveLeft(state.getMatrix())) {
                moveLeft(state.getMatrix(), 'E');
            }else {
                if(state.getLineH() > atacanteRow) {
                    if (canMoveDown(state.getMatrix())) {
                        moveDown(state.getMatrix(), 'E');
                    }
                }else
                if(state.getLineH() < atacanteRow) {
                    if (canMoveUp(state.getMatrix())) {
                        moveUp(state.getMatrix(), 'E');
                    }
                }
            }
        }else
        if(state.getLineH() > atacanteRow) {
            if (canMoveDown(state.getMatrix())) {
                moveDown(state.getMatrix(), 'E');
            }
        }else
        if(state.getLineH() < atacanteRow) {
            if (canMoveUp(state.getMatrix())) {
                moveUp(state.getMatrix(), 'E');
            }
        }
        atacanteMorre();
        if (state.getColumnH()==atacanteColl && state.getLineH()== atacanteRow){
            return true;
        }
        return false;

    }

}