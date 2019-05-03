package chapter7to8_Graph;

public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int either(){
        return v;
    }

    public int other(int v) {
        if (v == this.v)
            return w;
        return this.v;
    }

    public int compareTo(Edge that) {
        if (weight < that.weight)
            return -1;
        else if (weight > that.weight)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "v=" + v +
                ", w=" + w +
                ", weight=" + weight +
                '}';
    }
}
