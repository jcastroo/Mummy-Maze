package MummyMaze;

import agent.Action;
import agent.Problem;

import java.util.ArrayList;
import java.util.List;

public class MummyMazeProblem extends Problem<MummyMazeState> {

    //TODO
    //Alínea C


    public MummyMazeProblem(MummyMazeState initialState) {
        super(initialState, new ArrayList<>(5));

        super.actions.add(new ActionStop());
        super.actions.add(new ActionUp());
        super.actions.add(new ActionRight());
        super.actions.add(new ActionDown());
        super.actions.add(new ActionLeft());


    }

    //devover a lista de estados sucessores (lista de estados por onde é possivel atravessar para qualquer passo)
    @Override
    public List<MummyMazeState> executeActions(MummyMazeState state) { //Devolve a lista de estados sucessores
    //ARVORES QUE TESTAM TODAS AS HIPOTESES E DESENHA A LISTA DE AÇOES NECESSÁRIAS

        ArrayList<MummyMazeState> successors = new ArrayList<>(4);

        //para cada ação disponivel
        for (Action action : actions) {
            // se a ação for válida,
            if(action.isValid(state)) {
                //criar um novo estado sucessor
                MummyMazeState successor = (MummyMazeState) state.clone();
                //executar a ação sobre o novo estado
                successor.executeAction(action);
                //adicionar o novo estado à lista de sucessores
                if(!successor.ishMorto()) {
                    successors.add(successor);
                }
            }
        }
        //devolver a lista de estados sucessores
        return successors;
    }

    //Pesquisa el largur -> Conceiro de pilha
    //pesquisa em profundidade ->Conceito de fila

    @Override
    public boolean isGoal(MummyMazeState state) {
        return state.getLineH()==state.getLineDoor() && state.getColumnH() == state.getColumnDoor(); //TODO Passar a considerar a posição do heroi (linha coluna ao lado da porta)
    }

    @Override
    protected double computePathCost(List<Action> path) {
        return path.size(); //porque as acoes do EigthPuzzle têm todas custo 1
    }

}
