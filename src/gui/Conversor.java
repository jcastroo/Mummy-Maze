package gui;

public enum Conversor {
    CONVERSOR;

    public char[][] strToMatrix(String string){

        char[][] matrix = new char[13][13];
        string = string.replace("\n","");
        int ac = 0;
        int j;
        for(int i = 0; i < 13 ; i++){
            for( j = 0 ; j < 13; j++){
                matrix[i][j] = string.charAt(ac);
                ac++;
            }
        }
        return matrix;
    }

    public String matrixToString(char[][] matrix){
        String state = "";
        for(int i = 0; i < matrix[0].length ; i++){
            for( int j = 0 ; j < matrix[0].length; j++){
                state = state+matrix[i][j];
            }
            state = state+"\n";
        }
        return state;
    }
}
