package chapter7to8_Graph;

public class FlowEdge {

    private final int v, w;
    private final double capacity;
    private double flow;

    public FlowEdge(int v, int w, double capacity){
        this.v = v;
        this.w = w;
        this.capacity = capacity;
    }

    public int from(){return v;}

    public int to(){return w;}

    public int other(int i) {
        if (i == v) return w;
        else return v;
    }

    public double capacity(){return capacity;}

    public double flow(){return flow;}

    public double residualCapacityTo(int i) {
        if (i == v) return flow;
        else return capacity - flow;
    }

    public void addResidualFlowTo(int i, double delta) {
        if (i == v) flow -= delta;
        else flow += delta;
    }
}
