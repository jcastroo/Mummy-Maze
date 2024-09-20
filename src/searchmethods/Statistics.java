package searchmethods;

public class Statistics {
    public int numExpandedNodes;
    public int numGeneratedNodes = 1; //due to the initial node
    public int maxFrontierSize;
    public double duration;
    
    public void reset(){
        numExpandedNodes = 0;
        numGeneratedNodes = 1;
        maxFrontierSize = 0;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public double getDuration() {
        return duration / (double) 1000;
    }
}
