package gui;


import MummyMaze.MummyMazeEvent;
import MummyMaze.MummyMazeListener;
import MummyMaze.MummyMazeState;

import javax.swing.table.AbstractTableModel;

public class MummyMazeModel extends AbstractTableModel implements MummyMazeListener {

    private MummyMazeState puzzle;

    public MummyMazeModel(MummyMazeState puzzle) {
        if(puzzle == null){
            throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle = puzzle;
        this.puzzle.addListener(this);
    }

    @Override
    public int getColumnCount() {
        return puzzle.getNumLines();
    }

    @Override
    public int getRowCount() {
        return puzzle.getNumColumns();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return puzzle.getTileValue(row, col);
    }

    @Override
    public void puzzleChanged(MummyMazeEvent pe){
        fireTableDataChanged();
        try{
            Thread.sleep(500);
        }catch(InterruptedException ignore){
        }
    }

    public void setPuzzle(MummyMazeState puzzle){
        if(puzzle == null){
          throw new NullPointerException("Puzzle cannot be null");
        }
        this.puzzle.removeListener(this);
        this.puzzle = puzzle;
        puzzle.addListener(this);
        fireTableDataChanged();
    }
}
