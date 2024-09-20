package MummyMaze.ElementosDeJogo;

import MummyMaze.MummyMazeState;

public class Elemento {
    //protected MummyMazeState state;
    protected int elementRow;
    protected int elementCol;

    public Elemento(int row, int col){
        //this.state = state;
        this.elementRow = row;
        this.elementCol = col;
    }

    public int getCol() {
        return elementCol;
    }

    public int getRow() {
        return elementRow;
    }
}
