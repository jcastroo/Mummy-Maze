package MummyMaze.ElementosDeJogo;

import MummyMaze.MummyMazeState;

import java.util.LinkedList;

public class Chave extends Elemento {

    private LinkedList<Elemento> portasDaChave;

    public Chave( LinkedList<Elemento> portas, int row, int col) {
        super(row, col);
        this.portasDaChave = new LinkedList<>(portas);
    }

    public void setPortasDaChave(LinkedList<Elemento> portasDaChave) {
        this.portasDaChave = portasDaChave;
    }

    public void executar(char[][] matrix, MummyMazeState state) {

        for (Elemento p : portasDaChave) {

            if (matrix[p.getRow()][p.getCol()] == '_') {
                matrix[p.getRow()][p.getCol()] = '=';
            } else if (matrix[p.getRow()][p.getCol()] == ')') {
                matrix[p.getRow()][p.getCol()] = '"';
            } else if (matrix[p.getRow()][p.getCol()] == '=') {
                matrix[p.getRow()][p.getCol()] = '_';
            } else if (matrix[p.getRow()][p.getCol()] == '"') {
                matrix[p.getRow()][p.getCol()] = ')';
            }

            state.setMatrix(matrix);

        }
    }
}
