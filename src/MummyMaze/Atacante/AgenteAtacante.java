package MummyMaze.Atacante;

import MummyMaze.MummyMazeState;

import java.util.LinkedList;

public abstract class AgenteAtacante {
    protected MummyMazeState state;
    protected int atacanteColl;
    protected int atacanteRow;
    private int poder;
    protected char caracterOndeEstou;
    private char caracterAtacante;

    public AgenteAtacante(MummyMazeState state, int row, int coll, int poder, char caracterAtacante){
        this.poder = poder;
        this.state = state;
        this.atacanteRow = row;
        this.atacanteColl = coll;
        this.caracterOndeEstou = '.';
        this.caracterAtacante = caracterAtacante;

    }

    public int getAtacanteColl() {
        return atacanteColl;
    }

    public int getAtacanteRow() {
        return atacanteRow;
    }

    public char getCaracterAtacante() {
        return caracterAtacante;
    }

    public boolean efetuarJogada(){

        boolean x = jogar();
        return x;
    }

    public int getPoder() {
        return poder;
    }


    public void moveRight(char[][] matrix,char atacante){
        //matrix[atacanteRow][atacanteColl]='.';
        int aux = atacanteColl;
        conservaCaracter(matrix,atacanteRow,aux+2);
        matrix[atacanteRow][aux+2]=atacante;
        this.atacanteColl+=2;
        if(caracterOndeEstou == 'C' && (caracterAtacante == 'M' || caracterAtacante == 'V')){
            state.getChave().executar(state.getMatrix(), state);
        }
        //state.setMatrix(matrix);
        //checkVivo();

    }
    public void moveLeft(char[][] matrix,char atacante){
        //matrix[atacanteRow][atacanteColl]='.';
        int aux = atacanteColl;
        conservaCaracter(matrix,atacanteRow,aux-2);
        matrix[atacanteRow][aux-2]=atacante;
        this.atacanteColl-=2;
        if(caracterOndeEstou == 'C' && (caracterAtacante == 'M' || caracterAtacante == 'V')){
            state.getChave().executar(state.getMatrix(), state);
        }
        //state.setMatrix(matrix);
        //checkVivo();
    }
    public void moveUp(char[][] matrix,char atacante){
        //matrix[atacanteRow][atacanteColl]='.';
        int aux = atacanteRow;
        conservaCaracter(matrix,aux-2,atacanteColl);
        matrix[aux-2][atacanteColl]=atacante;
        this.atacanteRow-=2;
        if(caracterOndeEstou == 'C' && (caracterAtacante == 'M' || caracterAtacante == 'V')){
            state.getChave().executar(state.getMatrix(), state);
        }
        //state.setMatrix(matrix);
        //checkVivo();
    }

    public void moveDown(char[][] matrix,char atacante){
        int aux = atacanteRow;
        conservaCaracter(matrix,aux+2,atacanteColl);
        matrix[aux+2][atacanteColl]=atacante;
        this.atacanteRow+=2;
        if(caracterOndeEstou == 'C' && (caracterAtacante == 'M' || caracterAtacante == 'V')){
            state.getChave().executar(state.getMatrix(), state);
        }
    }

    public void conservaCaracter(char[][] matrix, int x,int y){
            matrix[atacanteRow][atacanteColl]='.';
            caracterOndeEstou = matrix[x][y];
    }

    public boolean canMoveRight(char[][] matrix) {
        return atacanteColl < 11 && matrix[atacanteRow][atacanteColl + 1] != '|' && matrix[atacanteRow][atacanteColl + 1] != '"';
    }
    public boolean canMoveLeft(char[][] matrix) {
        return atacanteColl > 1 && matrix[atacanteRow][atacanteColl-1] != '|'&& matrix[atacanteRow][atacanteColl-1] != '"';
    }
    public boolean canMoveUp(char[][] matrix) {
        return atacanteRow > 1 && matrix[atacanteRow-1][atacanteColl] != '-'&& matrix[atacanteRow-1][atacanteColl] != '=';
    }

    public boolean canMoveDown(char[][] matrix) {
        return atacanteRow < 11 && matrix[atacanteRow+1][atacanteColl] != '-'&& matrix[atacanteRow+1][atacanteColl] != '=';
    }
    protected boolean atacanteMorre(){
        LinkedList<AgenteAtacante> atacantesDoInicio = state.getAtacantesDoInicio();
        int quantAtacantes = atacantesDoInicio.size();
        char[][] matrix= state.getMatrix();

        for (int i = 0; i < quantAtacantes; i++) {
            for (int j = 0; j < quantAtacantes-i; j++) {

                if( i!=j && atacantesDoInicio.get(i).getAtacanteColl() == atacantesDoInicio.get(j).getAtacanteColl() && atacantesDoInicio.get(i).getAtacanteRow() == atacantesDoInicio.get(j).getAtacanteRow()){
                    //remover atacante
                    AgenteAtacante aRemovido;
                    AgenteAtacante aVivo;
                    if(atacantesDoInicio.get(i).getPoder() < atacantesDoInicio.get(j).getPoder()) {
                        aVivo= atacantesDoInicio.get(j);
                        aRemovido = atacantesDoInicio.remove(i);//TODO criar uma copia da lista de atacantes, devolve-la e atualizae depois a lista de atacantes no estado
                        matrix[atacantesDoInicio.get(i).getAtacanteRow()] [atacantesDoInicio.get(i).getAtacanteColl()] = aVivo.getCaracterAtacante();
                        i--;
                    }else{
                        aVivo= atacantesDoInicio.get(i);
                        aRemovido = atacantesDoInicio.remove(j);
                        matrix[atacantesDoInicio.get(i).getAtacanteRow()] [atacantesDoInicio.get(i).getAtacanteColl()] = aVivo.getCaracterAtacante();
                        j--;
                    }
                    //matrix[aRemovido.getAtacanteRow()][aRemovido.getAtacanteColl()] = '.';
                    //System.out.println("1 ATACANTE MORTO"+atacantesDoInicio);
                    quantAtacantes--;
                }
            }
        }
        return false;

    }

   /* protected boolean atacanteMorre(){
        LinkedList<AgenteAtacante> atacantesDoInicio = state.getAtacantesDoInicio();
        int quantAtacantes = atacantesDoInicio.size();
        char[][] matrix= state.getMatrix();
        for (int i = 0; i < quantAtacantes; i++) {
            for (int j = 0; j < quantAtacantes - i; j++) {
                //if (quantAtacantes >= 2) {
                    if (i != j && atacantesDoInicio.get(i).getAtacanteColl() == atacantesDoInicio.get(j).getAtacanteColl() && atacantesDoInicio.get(i).getAtacanteRow() == atacantesDoInicio.get(j).getAtacanteRow()) {
                        //remover atacante
                        AgenteAtacante aRemovido;
                        AgenteAtacante aVivo;
                        if (atacantesDoInicio.get(i).getPoder() < atacantesDoInicio.get(j).getPoder()) {
                            aVivo = atacantesDoInicio.get(j);
                            matrix[aVivo.getAtacanteRow()][aVivo.getAtacanteColl()] = '.';
                            caracterOndeEstou = '.';
                            //aRemovido = atacantesDoInicio.remove(i);//TODO criar uma copia da lista de atacantes, devolve-la e atualizae depois a lista de atacantes no estado
                            matrix[atacantesDoInicio.get(i).getAtacanteRow()][atacantesDoInicio.get(i).getAtacanteColl()] = aVivo.getCaracterAtacante();
                            i--;
                        } else {
                            aVivo = atacantesDoInicio.get(i);
                            //matrix[aVivo.getAtacanteRow()][aVivo.getAtacanteColl()] = '.';
                            caracterOndeEstou = '.';
                            aRemovido = atacantesDoInicio.remove(j);
                            matrix[atacantesDoInicio.get(j).getAtacanteRow()][atacantesDoInicio.get(j).getAtacanteColl()] = aVivo.getCaracterAtacante();
                            j--;
                        }
                        //matrix[aRemovido.getAtacanteRow()][aRemovido.getAtacanteColl()] = '.';
                        quantAtacantes--;
                    }
                //}
            }
        }


        return false;

    }*/



    public abstract boolean jogar();
}
