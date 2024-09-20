package MummyMaze;

import agent.Agent;
import java.io.File;
import java.io.IOException;

public class MummyMazeAgent extends Agent<MummyMazeState>{
    
    protected MummyMazeState initialEnvironment;
    
    public MummyMazeAgent(MummyMazeState environemt) {
        super(environemt);
        initialEnvironment = new MummyMazeState(environment.getMatrix());//(MummyMazeState) environemt.clone();
        heuristics.add(new HeuristicDoorDistance());
        heuristics.add(new HeuristicLowerCost());
        heuristics.add(new HeuristicEnemyDistance());


        heuristic = heuristics.get(0);
    }
            
    public MummyMazeState resetEnvironment(){
        environment = (MummyMazeState) initialEnvironment.clone();
        return environment;
    }
                 
    public MummyMazeState readInitialStateFromFile(File file) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(file);

        char[][] matrix = new char[13][13];

        for (int i = 0; i < 13; i++) {
            matrix[i] =  scanner.nextLine().toCharArray();

        }
        initialEnvironment = new MummyMazeState(matrix);
        resetEnvironment();
        return environment;
    }
}
