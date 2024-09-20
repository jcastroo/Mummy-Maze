package MummyMaze;

import MummyMaze.Atacante.*;
import MummyMaze.ElementosDeJogo.Armadilha;
import MummyMaze.ElementosDeJogo.Chave;
import MummyMaze.ElementosDeJogo.Elemento;
import agent.Action;
import agent.State;
import gui.Conversor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class MummyMazeState extends State implements Cloneable { //Clonable cria copias de um objeto (devolve um objeto igual ma com nova referencia)

    public static final int SIZE = 3;
    private char[][] matrix; //vai dexar de ser constante
    private int lineH;
    private int columnH;
    private int lineDoor;
    private int columnDoor;
    private Action acao;
    private LinkedList<AgenteAtacante> atacantesDoInicio;
    /*private boolean hasChave;
    private Elemento chave;*/
    private Chave chave;
    private LinkedList<Armadilha> armadilhas;
    private LinkedList<Elemento> portas;
    private boolean hMorto;
    private char caracterOndeEstou;


    public int getLineDoor() {
        return lineDoor;
    }

    public void setLineDoor(int lineDoor) {
        this.lineDoor = lineDoor;
    }

    public int getColumnDoor() {
        return columnDoor;
    }

    public void setColumnDoor(int columnDoor) {
        this.columnDoor = columnDoor;
    }

    public int getLineH() {
        return lineH;
    }

    public void setLineH(int lineH) {
        this.lineH = lineH;
    }

    public int getColumnH() {
        return columnH;
    }

    public void setColumnH(int columnH) {
        this.columnH = columnH;
    }

    public MummyMazeState(char[][] matrix) {
        this.matrix = new char[matrix.length][matrix.length];
        atacantesDoInicio = new LinkedList<>();
        portas = new LinkedList<>();
        armadilhas = new LinkedList<>();
        hMorto = false;
        caracterOndeEstou = '.';
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == 'H') {
                    lineH = i;
                    columnH = j;
                }
                if (this.matrix[i][j] == 'S') {
                    lineDoor = i;
                    columnDoor = j;
                }
                if (this.matrix[i][j] == 'M') {
                    atacantesDoInicio.add(new MumiaBranca(this, i, j, 2));
                }
                if (this.matrix[i][j] == 'E') {
                    atacantesDoInicio.add(new Escorpiao(this, i, j, 1));
                }
                if (this.matrix[i][j] == 'V') {
                    atacantesDoInicio.add(new MumiaVermelha(this, i, j, 3));
                }
                if (this.matrix[i][j] == '=') {
                    portas.add(new Elemento(i, j));
                }
                if (this.matrix[i][j] == '_') {
                    portas.add(new Elemento(i, j));
                }
                if (this.matrix[i][j] == '"') {
                    portas.add(new Elemento(i, j));
                }
                if (this.matrix[i][j] == ')') {
                    portas.add(new Elemento(i, j));
                }
                if (this.matrix[i][j] == 'C') {
                    chave = new Chave(portas, i, j);
                    chave.setPortasDaChave(portas);
                }
                if (this.matrix[i][j] == 'A') {
                    armadilhas.add(new Armadilha(i, j));
                }
            }

        }

    }

    public MummyMazeState(char[][] matrix, MummyMazeState oldState) {
        //TODO Modificar a visivilidade dos atributos

        this.lineH = oldState.lineH;
        this.columnH = oldState.columnH;
        this.lineDoor = oldState.lineDoor;
        this.columnDoor = oldState.columnDoor;

        this.matrix = Conversor.CONVERSOR.strToMatrix(Conversor.CONVERSOR.matrixToString(oldState.matrix));
        this.hMorto = oldState.hMorto;
        this.portas = oldState.portas;
        this.atacantesDoInicio = new LinkedList<>();
        this.armadilhas = oldState.armadilhas;
        this.chave = oldState.chave;
        this.caracterOndeEstou = oldState.caracterOndeEstou;


        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                if (this.matrix[i][j] == 'S') {
                    lineDoor = i;
                    columnDoor = j;
                }
                if (matrix[i][j] == 'M') {
                    atacantesDoInicio.add(new MumiaBranca(this, i, j, 2));
                }
                if (matrix[i][j] == 'E') {
                    atacantesDoInicio.add(new Escorpiao(this, i, j, 1));
                }
                if (matrix[i][j] == 'V') {
                    atacantesDoInicio.add(new MumiaVermelha(this, i, j, 3));
                }

            }
        }
    }

    @Override
    public void executeAction(Action action) {
        MummyMazeState s = this;
        acao = action;//TODO  *********
        action.execute(s);
        firePuzzleChanged(null);
    }

    public char[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(char[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public Action getAction() {
        return acao;
    }

    public Chave getChave() {
        return chave;
    }

    private boolean verificaArmadilhas(int x, int y) {
        for (Armadilha a : armadilhas) {
            if (a.getRow() == x && a.getCol() == y) {
                return false;
            }
        }
        return true;
    }

    public boolean canMoveUp() {
        if (lineH == 1 && lineH - 1 == lineDoor && columnDoor == columnH) {
            return true;
        }

        return lineH > 1 && matrix[lineH - 1][columnH] != '-' && matrix[lineH - 1][columnH] != '=' && !verificaSeAtacante(lineH - 2, columnH) && verificaArmadilhas(lineH - 2, columnH) && !hMorto;
    }

    public boolean canMoveRight() {
        if (columnH == 11 && columnH + 1 == columnDoor && lineH == lineDoor) {
            return true;
        }
        return columnH < matrix.length - 2 && matrix[lineH][columnH + 1] != '|' && matrix[lineH][columnH + 1] != '"' && !verificaSeAtacante(lineH, columnH + 2) && verificaArmadilhas(lineH, columnH + 2) && !hMorto;
    }

    public boolean canMoveDown() {
        if (lineH == 11 && lineH + 1 == lineDoor && columnDoor == columnH) {
            return true;
        }
        return lineH < matrix.length - 2 && matrix[lineH + 1][columnH] != '-' && matrix[lineH + 1][columnH] != '=' && !verificaSeAtacante(lineH + 2, columnH) && verificaArmadilhas(lineH + 2, columnH) && !hMorto;
    }

    public boolean canMoveLeft() {
        if (columnH == 1 && columnH - 1 == columnDoor && lineH == lineDoor) {
            return true;
        }
        return columnH > 1 && matrix[lineH][columnH - 1] != '|' && matrix[lineH][columnH - 1] != '"' && !verificaSeAtacante(lineH, columnH - 2) && verificaArmadilhas(lineH, columnH - 2) && !hMorto;
    }

    private boolean verificaSeAtacante(int l, int c) {
        for (AgenteAtacante a : atacantesDoInicio) {
            if (c == a.getAtacanteColl() && l == a.getAtacanteRow()) {
                return true;
            }
        }
        return false;
    }

    public void conservaCaracter() {
        if (chave != null && matrix[chave.getRow()][chave.getCol()] == '.')
            matrix[chave.getRow()][chave.getCol()] = 'C';

        for (Armadilha armadilha : armadilhas) {
            if(matrix[armadilha.getRow()][armadilha.getCol()] == '.')
                matrix[armadilha.getRow()][armadilha.getCol()] = 'A';
        }

    }

    public void moveUp() {
        matrix[lineH][columnH] = '.';
        if (lineH == 1 && matrix[lineH - 1][columnH] == 'S') {

            this.lineH--;
        } else if (lineH <= 11 && lineH > 1 && columnH <= 11 && columnH >= 1) {

            this.lineH -= 2;
        }
        matrix[lineH][columnH] = 'H';
        int tamanho = atacantesDoInicio.size();
        for (int i = 0; i < tamanho; i++) {
            hMorto = atacantesDoInicio.get(i).efetuarJogada();
            if (hMorto) return;
            tamanho = atacantesDoInicio.size();
        }
        if (chave != null && lineH == chave.getRow() && columnH == chave.getCol()) {
            chave.executar(matrix, this);
        }
        conservaCaracter();
    }

    public void moveRight() {
        matrix[lineH][columnH] = '.';
        if (columnH == 11 && matrix[lineH][columnH + 1] == 'S') {

            this.columnH++;
        } else if (columnH < 11 && columnH >= 1 && lineH <= 11 && lineH >= 1) {

            this.columnH += 2;
        }
        matrix[lineH][columnH] = 'H';
        int tamanho = atacantesDoInicio.size();
        for (int i = 0; i < tamanho; i++) {
            hMorto = atacantesDoInicio.get(i).efetuarJogada();
            if (hMorto) return;
            tamanho = atacantesDoInicio.size();
        }

        if (chave != null && lineH == chave.getRow() && columnH == chave.getCol()) {
            chave.executar(matrix, this);
        }
        conservaCaracter();
    }

    public void moveDown() {
        matrix[lineH][columnH] = '.';
        if (lineH == 11 && matrix[lineH + 1][columnH] == 'S') {

            this.lineH++;
        } else if (lineH < 11 && lineH >= 1 && columnH <= 11 && columnH >= 1) {

            this.lineH += 2;
        }
        matrix[lineH][columnH] = 'H';
        int tamanho = atacantesDoInicio.size();
        for (int i = 0; i < tamanho; i++) {
            hMorto = atacantesDoInicio.get(i).efetuarJogada();
            if (hMorto) return;
            tamanho = atacantesDoInicio.size();
        }
        if (chave != null && lineH == chave.getRow() && columnH == chave.getCol()) {
            chave.executar(matrix, this);
        }
        conservaCaracter();
    }

    public void moveLeft() {
        matrix[lineH][columnH] = '.';
        int aux = columnH;
        if (columnH == 1 && matrix[lineH][columnH - 1] == 'S') {

            this.columnH--;
        } else if (columnH <= 11 && columnH > 1 && lineH <= 11 && lineH >= 1) {

            this.columnH -= 2;
        }
        matrix[lineH][columnH] = 'H';
        int tamanho = atacantesDoInicio.size();
        for (int i = 0; i < tamanho; i++) {
            hMorto = atacantesDoInicio.get(i).efetuarJogada();
            if (hMorto) return;
            tamanho = atacantesDoInicio.size();
        }
        if (chave != null && lineH == chave.getRow() && columnH == chave.getCol()) {
            chave.executar(matrix, this);
        }
        conservaCaracter();
    }

    public void moveStop() {

        int tamanho = atacantesDoInicio.size();
        for (int i = 0; i < tamanho; i++) {
            hMorto = atacantesDoInicio.get(i).efetuarJogada();
            if (hMorto) return;
            tamanho = atacantesDoInicio.size();
        }
        conservaCaracter();
    }

    public boolean ishMorto() {
        return hMorto;
    }

    public double computeTilesOutOfPlace(MummyMazeState finalState) {
        double tilesOutOfPlace = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    if (matrix[i][j] != finalState.matrix[i][j]) {
                        tilesOutOfPlace += 1;
                    }
                }
            }
        }

        return tilesOutOfPlace;
    }

    public double computeDoorDistance() {
        int h = 0;
        if (columnDoor == 0) {
            //porta na lateral esquerda
            h = Math.abs(columnDoor - columnH);
            h = auxHeuristicDooDstance(h);
        } else if (columnDoor == matrix.length - 1) {
            //porta na lateral direita
            h = columnDoor - columnH;
            h = auxHeuristicDooDstance(h);
        } else if (lineDoor == 0) {
            //porta do lado superior
            h = Math.abs(lineDoor - lineH);
            h = auxHeuristicDooDstance(h);
        } else if (lineDoor == matrix.length - 1) {
            //porta do lado inferior
            h = lineDoor - lineH;
            h = auxHeuristicDooDstance(h);
        }
        return h;
    }

    public double computeEnemyDistance() {
        int distancia = 0;
        int[] mapa = {20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1,0};
        for (AgenteAtacante atacante : atacantesDoInicio) {
            //somar a distância
            distancia += Math.abs(lineH-atacante.getAtacanteRow())+Math.abs(columnH- atacante.getAtacanteColl());
        }
        /*int h =
        System.out.println("valor -> "+h+"...\n"+Conversor.CONVERSOR.matrixToString(this.matrix));*/
        return distancia >= 20 ? 0 : mapa[distancia%20];
    }

    private int auxHeuristicDooDstance(int h) {
        if (lineH < getMatrix().length - 2 && matrix[lineH + 1][columnH] == '-') {
            h *= 2;
        }
        if (lineH > 1 && matrix[lineH - 1][columnH] == '-') {
            h *= 2;
        }
        if (columnH < matrix.length - 2 && matrix[lineH][columnH + 1] == '|') {
            h *= 2;
        }
        if (columnH > 1 && matrix[lineH][columnH - 1] == '|') {
            h *= 2;
        }
        return h;
    }


    public int lowercost(){
        return (int) this.action.getCost();
    }







    public int getNumLines() {
        return matrix.length;
    }

    public int getNumColumns() {
        return matrix[0].length;
    }

    public LinkedList<AgenteAtacante> getAtacantesDoInicio() {
        return atacantesDoInicio;
    }

    public int getTileValue(int line, int column) {
        if (!isValidPosition(line, column)) {
            throw new IndexOutOfBoundsException("Invalid position!");
        }
        return matrix[line][column];
    }

    public boolean isValidPosition(int line, int column) {
        return line >= 0 && line < matrix.length && column >= 0 && column < matrix[0].length;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof MummyMazeState)) {
            return false;
        }

        MummyMazeState o = (MummyMazeState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public Object clone() {
        return new MummyMazeState(matrix, this);
    }

    //Listeners
    private transient ArrayList<MummyMazeListener> listeners = new ArrayList<MummyMazeListener>(3);

    public synchronized void removeListener(MummyMazeListener l) {
        if (listeners != null && listeners.contains(l)) {
            listeners.remove(l);
        }
    }

    public synchronized void addListener(MummyMazeListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public void firePuzzleChanged(MummyMazeEvent pe) {
        for (MummyMazeListener listener : listeners) {
            listener.puzzleChanged(null);
        }
    }


}
